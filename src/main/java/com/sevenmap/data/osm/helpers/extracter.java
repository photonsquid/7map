package com.sevenmap.data.osm.helpers;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;

import com.sevenmap.data.osm.Node;

import org.jdom.Element;
import org.reflections.ReflectionUtils;

public class extracter {

  public static Node extractData(Element racine, Class<?> clazz) {

    getAttribute(i, clazz);

    while (i.hasNext()) {

    }

    return new Node();

  }

  private static HashMap<Field, Object> getAttribute(Iterator<?> i, Class<?> clazz) {
    if (clazz.getClass().isAnnotationPresent(XMLClass.class)) {
      Set<Field> fields = ReflectionUtils.getAllFields(clazz.getClass(), new Predicate() {
        @Override
        public boolean test(Object input) {
          return true;
        }
      });
      for (Field f : fields) {
        System.out.println(f.getClass());
        // getAttribute(i, f.getClass());
      }
      return new HashMap<Field, Object>();
    } else {
      System.out.println("coucou");
      return new HashMap<Field, Object>();
    }

  }

}
