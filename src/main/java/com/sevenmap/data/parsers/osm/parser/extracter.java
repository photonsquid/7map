package com.sevenmap.data.parsers.osm.parser;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sevenmap.data.parsers.Parser;
import com.sevenmap.data.parsers.osm.Elements.Metadata.Metadata;
import com.sevenmap.data.parsers.osm.parser.Annotations.XMLAttribute;
import com.sevenmap.data.parsers.osm.parser.Annotations.XMLElement;

import org.apache.commons.beanutils.BeanUtils;
import org.jdom.Element;
import org.jdom.filter.ElementFilter;

public class extracter extends Parser {

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

  // TODO: javadoc
  private String getAnnotationValue(String possibleNullValue, String defaultValue) {
    String returnValue = possibleNullValue;
    if (returnValue.equals(""))
      returnValue = defaultValue;
    return returnValue;
  }

}
