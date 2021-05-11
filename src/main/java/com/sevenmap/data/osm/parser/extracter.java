package com.sevenmap.data.osm.parser;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sevenmap.data.osm.parser.Annotations.XMLAttribute;
import com.sevenmap.data.osm.parser.Annotations.XMLClass;
import com.sevenmap.data.osm.parser.Annotations.XMLElement;

import org.apache.commons.beanutils.BeanUtils;
import org.jdom.Element;
import org.jdom.filter.ElementFilter;

public class extracter {

  private <T> void setAttributes(Element element, T obj) {

    for (Field f : obj.getClass().getDeclaredFields()) {
      XMLAttribute attribute = f.getAnnotation(XMLAttribute.class);
      String attributeName = "";
      if (attribute != null) {
        attributeName = attribute.name();
        if (attributeName.equals(""))
          attributeName = f.getName();
        try {

          String value = element.getAttribute(attributeName).getValue();
          Object valueCasted = value;

          if (Integer.class.isAssignableFrom(f.getType())) {
            valueCasted = Integer.parseInt(value);
          } else if (Double.class.isAssignableFrom(f.getType())) {
            valueCasted = Double.parseDouble(value);
          } else if (Long.class.isAssignableFrom(f.getType())) {
            valueCasted = Long.parseLong(value);
          } else if (Float.class.isAssignableFrom(f.getType())) {
            valueCasted = Float.parseFloat(value);
          } else if (Boolean.class.isAssignableFrom(f.getType())) {
            valueCasted = Boolean.parseBoolean(value);
          }

          BeanUtils.setProperty(obj, f.getName(), valueCasted);
        } catch (IllegalAccessException e) {
          // TODO: handle Error
          e.printStackTrace();
        } catch (InvocationTargetException e) {
          // TODO: handle Error
          e.printStackTrace();
        }
      }
    }

  }

  private <T> void setElements(Element element, T obj) {

    for (Field f : obj.getClass().getDeclaredFields()) {
      XMLElement attribute = f.getAnnotation(XMLElement.class);
      String attributeTag = "";
      if (attribute != null) {
        attributeTag = attribute.tag();
        if (attributeTag.equals(""))
          attributeTag = f.getName();

        Class<?> fieldClass;
        try {

          System.out.println();
          System.out.println(f.getType().getName());

          fieldClass = Class.forName(f.getType().getName());
          Object a = fieldClass.getDeclaredConstructor().newInstance();

          if (a instanceof Collection || a instanceof Map) {
            if (a instanceof Map) {
              HashMap<?, ?> newHashMap = handleMap(attribute.valueType(), attribute.keyType(), element);

              BeanUtils.setProperty(obj, f.getName(), newHashMap);
            }
          } else {
            setData(element, a);
            BeanUtils.setProperty(obj, f.getName(), a);
          }

        } catch (Exception e) {
          // TODO: handle errors
          e.printStackTrace();
        }

      }
    }

  }

  private <K, V> HashMap<K, V> handleMap(Class<K> keyClass, Class<V> valueClass, Element el) {
    // Create an empty HashMap that will be filled
    HashMap<K, V> maps = new HashMap<K, V>();

    try {
      List<?> noeuds = el.getContent(new ElementFilter("tag"));
      Iterator<?> i = noeuds.iterator();
      while (i.hasNext()) {
        K key = keyClass.getDeclaredConstructor().newInstance();
        V value = valueClass.getDeclaredConstructor().newInstance();
        setData((Element) i.next(), value);
        maps.put(key, value);
      }
    } catch (Exception e) {
      // TODO: handle exception
    }

    return maps;
  }

  private <T> T setData(Element el, T obj) {

    //
    setAttributes(el, obj);
    setElements(el, obj);

    return obj;
  }

  public <T> T extract(Element root, Class<T> className) {
    // Create main object that will be returned.
    T returnObj = createObjectFrom(className);

    // Analyse root and apply it to returnObj
    return setData(root, returnObj);
  }

  @SuppressWarnings("unchecked")
  private <T> T createObjectFrom(Class<T> className) {
    Object obj = new Object();

    // Initialize it
    try {
      obj = className.getDeclaredConstructor().newInstance();
    } catch (Exception e) {
      // TODO: handle exception
    }

    return (T) obj;
  }

  /**
   * Return the key value of the XMLClass annotation. If there is none, the class
   * name of clazz is return.
   * 
   * @param clazz The class you want the key value
   * @return key value of the XMLClass annotation
   */
  private String getXMLKeyName(Class<?> clazz) {
    String XMLClassKey = "";
    Annotation[] annotations = clazz.getAnnotations();
    for (Annotation annotation : annotations) {
      if (annotation instanceof XMLClass) {
        XMLClass myAnnotation = (XMLClass) annotation;
        XMLClassKey = myAnnotation.key();
      }
    }
    if (XMLClassKey.equals("")) {
      XMLClassKey = clazz.getSimpleName();
    }
    return XMLClassKey;
  }

}
