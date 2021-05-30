package com.sevenmap.data.parsers.osm;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sevenmap.core.Props;
import com.sevenmap.data.objsept.GeographicCoord;
import com.sevenmap.data.objsept.PlainMap;
import com.sevenmap.data.objsept.Portal;
import com.sevenmap.data.parsers.MapParser;
import com.sevenmap.data.parsers.json.JsonParser;
import com.sevenmap.data.parsers.osm.Annotations.XMLAttribute;
import com.sevenmap.data.parsers.osm.Annotations.XMLElement;
import com.sevenmap.data.parsers.osm.Structure.Root;
import com.sevenmap.data.parsers.osm.Structure.Member.Member;
import com.sevenmap.data.parsers.osm.Structure.Metadata.Metadata;
import com.sevenmap.data.parsers.osm.Structure.Node.Nd;
import com.sevenmap.data.parsers.osm.Structure.Node.Node;
import com.sevenmap.data.parsers.osm.Structure.Relation.Relation;
import com.sevenmap.data.parsers.osm.Structure.Way.Way;
import com.sevenmap.data.styles.AssetStyle;
import com.sevenmap.data.styles.Styles;
import com.sevenmap.spinel.elements.geom.Item;
import com.sevenmap.spinel.math.Vector3f;

import org.apache.commons.beanutils.BeanUtils;
import org.jdom.Element;
import org.jdom.filter.ElementFilter;
import org.jdom.input.SAXBuilder;

public class OSM extends MapParser {
  private Root rt;

  private static org.jdom.Document doc;

  // <------------------------------- Constructor ------------------------------->
  public OSM() {
  }

  public OSM(Props props) {
    super(props);
  }

  // <-------------------------------- Code logic ------------------------------->

  public static URL generateURL(Props props) throws MalformedURLException {
    return new URL(props.getOSM_API_ROOT_URL() + props.getMaxLat() + "," + props.getMaxLon() + "," + props.getMinLat()
        + "," + props.getMinLon());
  }

  public void parse() {

    // Open document
    SAXBuilder sxb = new SAXBuilder();
    try {
      // if it is a reboot, load demo from jar file:
      if (props.getMapFile() == null) {

        InputStream is = getClass().getResourceAsStream(props.getDefaultMapFile());
        doc = sxb.build(is);
      } else {
        File mapFile = new File(props.getMapFile());
        doc = sxb.build(mapFile);
      }
    } catch (Exception e) {
      // TODO: handle error
      System.out.printf("erreur. chemin invalide (%s)\n", props.getMapFile());
    }

    // Parse
    try {
      this.rt = extract(doc.getRootElement(), Root.class);

    } catch (Exception e) {
      // TODO: handle errors
      e.printStackTrace();
    }

  }

  @SuppressWarnings("unchecked")
  private <K, V> HashMap<K, V> handleMap(XMLElement XML_Element, Element el, String tagName) {
    // Create an empty HashMap that will be filled
    HashMap<K, V> map = new HashMap<K, V>();

    // Class of the key/value
    Class<?> keyClass = XML_Element.keyType();
    Class<?> valueClass = XML_Element.valueType();
    try {
      List<?> noeuds = el.getContent(new ElementFilter(tagName));
      Iterator<?> i = noeuds.iterator();

      while (i.hasNext()) {

        Element currentElement = (Element) i.next();

        // Create empty key and value
        K key = (K) createObjectFrom(keyClass);
        V value = (V) createObjectFrom(valueClass);

        // Read data from XML and affect it to `value`
        setData(currentElement, value);

        // Read key content from valueClass inspection
        key = (K) string2Type(currentElement.getAttributeValue(getKeyFromAnnotation(valueClass)), keyClass);

        // Add new element to map
        map.put(key, value);
      }
    } catch (Exception e) {
      // TODO: handle exception
    }

    return map;
  }

  @SuppressWarnings("unchecked")
  private <V> ArrayList<V> handleList(XMLElement XML_Element, Element el, String tagName) {
    // Create an empty ArrayList that will be filled
    ArrayList<V> list = new ArrayList<V>();

    // Class of the key
    Class<?> valueClass = XML_Element.valueType();

    try {
      List<?> noeuds = el.getContent(new ElementFilter(tagName));
      Iterator<?> i = noeuds.iterator();

      while (i.hasNext()) {

        Element currentElement = (Element) i.next();

        // Create empty key and value
        V value = (V) createObjectFrom(valueClass);

        // Read data from XML and affect it to `value`
        setData(currentElement, value);

        // Add new element to map
        list.add(value);
      }
    } catch (Exception e) {
      // TODO: handle exception
    }

    return list;
  }

  private <T> void analyseAttribute(Element el, T obj, Field f) {

    // Read field annotation of type XMLAttribute if there is one
    XMLAttribute XML_Attribute = f.getAnnotation(XMLAttribute.class);

    // If there is an annotation
    if (XML_Attribute != null) {

      // Get the name of the attribute
      String attributeName = getAnnotationValue(XML_Attribute.name(), f.getName());

      try {

        Object dataToSet;

        // Read in the current element of the XML doc, the value of the attribute that
        // as `attributeName` as name
        String value = el.getAttributeValue(attributeName);
        dataToSet = string2Type(value, f.getType());

        // If there is no attribute with this name, we go deeper
        if (value == null) {
          Class<?> fieldClass = Class.forName(f.getType().getName());
          dataToSet = createObjectFrom(fieldClass);
          setData(el, dataToSet);
        }

        // Set the field `f` of obj with the value after a cast to the actual type
        BeanUtils.setProperty(obj, f.getName(), dataToSet);

      } catch (Exception e) {
        // TODO: handle errors
        e.printStackTrace();
      }
    }
  }

  private <T> void analyseElement(Element el, T obj, Field f) {

    // Read field annotation of type XMLElement if there is one
    XMLElement XML_Element = f.getAnnotation(XMLElement.class);

    // If there is an annotation
    if (XML_Element != null) {

      // Get the tag name of the elemnt
      String tagName = getAnnotationValue(XML_Element.tag(), f.getName());

      try {

        // Create an empty object of the actual field's type
        // Warning: the type of `f` is java.lang.reflect.Field, and not the actual type
        // of the field, but the method `f.getType()` return the actual type.
        Class<?> fieldClass = Class.forName(f.getType().getName());
        Object newField = createObjectFrom(fieldClass);

        // Switch on this type
        // If it is a Collection or a Map, it will loop over all elements
        if (newField instanceof Collection || newField instanceof Map) {
          if (newField instanceof Map) {
            newField = handleMap(XML_Element, el, tagName);

          } else if (newField instanceof List) {
            newField = handleList(XML_Element, el, tagName);
          }
          // TODO: handle Map, List, ...

          BeanUtils.setProperty(obj, f.getName(), newField);
        }

        // Else, it just read and affect the element to the empty object
        else {
          List<?> noeuds = el.getContent(new ElementFilter(tagName));
          Iterator<?> i = noeuds.iterator();

          while (i.hasNext()) {
            Element currentElement = (Element) i.next();
            setData(currentElement, newField);
            BeanUtils.setProperty(obj, f.getName(), newField);
          }

          // TODO handle error if there is more than one loop
        }

      } catch (Exception e) {
        // TODO: handle errors
        e.printStackTrace();
      }

    }
  }

  private <T> void setData(Element el, T obj) {

    // Loop for each fields of obj
    for (Field f : obj.getClass().getDeclaredFields()) {

      // Analyse f as an attribute if it is
      analyseAttribute(el, obj, f);

      // Analyse f as an element if it is
      analyseElement(el, obj, f);
    }
  }

  public <T> T extract(Element root, Class<T> className) {
    // Create main object that will be returned.
    T returnObj = createObjectFrom(className);

    // Analyse root and apply its data to returnObj
    setData(root, returnObj);

    return returnObj;
  }

  /**
   * Convert a list of nodes to a list of feature quickly displayable
   */
  public void build() {

    // On définit les nouvelles coordonnées de chaque noeud.

    HashMap<Long, Relation> rels = rt.getRelations();
    HashMap<Long, Way> ways = rt.getWays();
    HashMap<Long, Node> nodes = rt.getNodes();
    generatedMap = new PlainMap<Long, Item>();

    // Parse styles.json
    if (props.getStyles() == null) {
      String filePath = props.getAppDataPath() + props.getSettingFile();
      Styles st = JsonParser.parse(filePath, Styles.class);
      this.props.setStyles(st);
    }

    // 1. iterate on all relations and build them
    buildRelations(rels);
    buildWays(ways);
    // Construire =
    // Convert points from lat long to (x, y, z)
    // Parse styles.json and apply it to each objects

    // 1. Itérer sur toutes relations et les construire :
    // 1.1 construire les routes
    // 1.2 si les routes ou points utilisés n'ont pas d'autres références, ils
    // doivent être supprimés du root pour ne pas être rendu deux fois.
    // 2. Itérer sur toutes les routes restantes et les construire, en supprimant
    // les bons noeuds
    // 3. Itérer sur tous les noeuds restant et les construire
    //

  }

  /**
   * Builds all relations present in the root Remove nodes and ways that only were
   * in ref of these relations.
   * 
   * @param rt root containing relations to build
   */
  private void buildRelations(HashMap<Long, Relation> rels) {
    // 1. Itérer sur toutes relations et les construire :
    // 1.1 construire les routes
    // 1.2 si les routes ou points utilisés n'ont pas d'autres références, ils
    // doivent être supprimés de la racine pour ne pas être rendu deux fois.
    for (Map.Entry<Long, Relation> entry : rels.entrySet()) {
      Long id = entry.getKey();
      Relation rel = entry.getValue();

      String type = rel.findTag("type").getValue();
      AssetStyle st = props.getStyles().findStyle(type, props.getTheme());

      // We don't want to handle objects that we didn't implement yet
      if (st != null) {

        ArrayList<Member> mbs = rel.getMembers();

        for (Member mb : mbs) {
          // Retrieve the member in the actual Root object
          Object obj = findObject(rt, mb.getType(), mb.getRef());
          if (obj instanceof Way) {
            Way wy = (Way) obj;
            buildWay(wy);
          }
        }
      }

    }

  }

  private void buildWays(HashMap<Long, Way> ways) {
    for (Map.Entry<Long, Way> entry : ways.entrySet()) {
      Long id = entry.getKey();
      Way wy = entry.getValue();

      String theme = props.getTheme();

      AssetStyle routeStyle = props.getStyles().findStyle("route", theme);
      AssetStyle polyStyle = props.getStyles().findStyle("poly", theme);

      // We don't want to handle objects that we didn't implemented yet
      if (routeStyle != null && polyStyle != null) {

        ArrayList<Nd> nds = wy.getNodes();

        ArrayList<Vector3f> listPoints = new ArrayList<Vector3f>();
        for (Nd nb : nds) {
          // Retrieve the member in the actual Root object
          Object obj = findObject(rt, "node", nb.getRef());
          if (obj instanceof Node) {
            Node node = (Node) obj;
            GeographicCoord coords = new GeographicCoord(node.getLat(), node.getLon());
            Vector3f v3 = GeoCoord2SpinelCoord(coords);
            listPoints.add(v3);
          }
        }
        if (listPoints.get(0).equals(listPoints.get(listPoints.size() - 1))) {
          generatedMap.put(id, Portal.loadRoad(listPoints, polyStyle));
        } else {
          generatedMap.put(id, Portal.loadRoad(listPoints, routeStyle));
        }
      }

    }
  }

  private void buildWay(Way wy) {

  }

  private void buildNode(Node nd) {

  }

  private void buildNodes() {

  }

  /**
   * Find the object whose class match tag according to Root
   * annotation(@XMLElement) and whose id attribute = ref
   * 
   * @param obj root object in which to search
   * @param tag tag of the object
   * @param ref id of the object
   * @return object (Way | Relation | Node) from rt that matches conditions
   */
  private <R, T, O> Object findObject(O obj, String tag, T ref) {

    // For all attributes in Root class
    for (Field f : obj.getClass().getDeclaredFields()) {

      // Get XMLElement annotation
      XMLElement XML_Element = f.getAnnotation(XMLElement.class);

      // If there is one
      if (XML_Element != null) {

        // Get tag name, it is necessarily either of XML_Element.tag, or the attribute
        // name.
        String tagName = getAnnotationValue(XML_Element.tag(), f.getName());

        // If tag correspond
        if (tagName.equals(tag)) {
          // `f` is the attribute in which to search for the tag.
          try {

            // Create an empty object of this attribute
            Class<?> fieldClass = Class.forName(f.getType().getName());
            Object field = createObjectFrom(fieldClass);

            // If it is a Map
            if (field instanceof HashMap) {

              // Search the item
              return getFromMap(obj, f.getName(), ref, XML_Element.getClass());
            }

          } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }

        }

      }

    }
    return null;

  }

  @SuppressWarnings("unchecked")
  private <V, K, O> Object getFromMap(O obj, String propertyName, Object keyValue, Class<K> keyClass) {
    // Cast key value to the right type
    K keyV = (K) keyValue;

    // get the actual value of attribute
    HashMap<K, V> field = new HashMap<K, V>();
    field = getProperty(obj, propertyName, field);

    // TODO handle not found
    V a = field.get(keyV);
    return a;

  }

  // <---------------------- Helpers ---------------------->

  // TODO: javadoc
  private <T> String getKeyFromAnnotation(Class<T> valueClass) {

    String keyName = "";

    // Possible TODO: transform for into while
    // Loop for each fields of valueClass
    for (Field f : valueClass.getDeclaredFields()) {

      // Read field annotation of type XMLAttribute if there is one
      XMLAttribute XML_Attribute = f.getAnnotation(XMLAttribute.class);

      // If there is an annotation
      if (XML_Attribute != null) {

        // If this attribute is a unique identifier
        if (XML_Attribute.unique()) {

          // save attribute name
          keyName = getAnnotationValue(XML_Attribute.name(), f.getName());
        }
      }
    }
    return keyName;
    // TODO: handle error if no unique attribute was found
  }

  // TODO: javadoc
  @SuppressWarnings("unchecked")
  private <T> T string2Type(String valueToCast, Class<T> classCasted) {
    Object valueCasted = valueToCast;

    if (Integer.class.isAssignableFrom(classCasted)) {
      valueCasted = Integer.parseInt(valueToCast);
    } else if (Double.class.isAssignableFrom(classCasted)) {
      valueCasted = Double.parseDouble(valueToCast);
    } else if (Long.class.isAssignableFrom(classCasted)) {
      valueCasted = Long.parseLong(valueToCast);
    } else if (Float.class.isAssignableFrom(classCasted)) {
      valueCasted = Float.parseFloat(valueToCast);
    } else if (Boolean.class.isAssignableFrom(classCasted)) {
      valueCasted = Boolean.parseBoolean(valueToCast);
    } else if (Date.class.isAssignableFrom(classCasted)) {
      valueCasted = Metadata.parseDate(valueToCast);
    }
    return (T) valueCasted;
  }

}
