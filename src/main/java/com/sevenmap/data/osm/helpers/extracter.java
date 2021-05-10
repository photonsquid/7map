package com.sevenmap.data.osm.helpers;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;

import com.sevenmap.data.osm.Node;

import org.reflections.ReflectionUtils;

public class extracter {

  public static Node extractData(Iterator<?> i, Class<?> className) {
    System.out.println(className.getName());
    Set<Field> fields = ReflectionUtils.getAllFields(className, new Predicate() {
      @Override
      public boolean test(Object input) {
        return true;
      }
    });

    // Map<Field, Object> values = ...
    for (Field f : fields) {
      // f.setAccessible(true);
      System.out.println(f.toString());

      // Nom de l'attribut
      System.out.println(f.getName());

      // Classe de l'attribut
      System.out.println("--------------");
      System.out.println("getAnnotatedType");
      System.out.println("--------------");
      AnnotatedType c = f.getAnnotatedType();
      System.out.println(c.toString());

      try {
        Class<?> act = Class.forName(c.toString());
        Set<Field> ff = ReflectionUtils.getAllFields(act, new Predicate() {
          @Override
          public boolean test(Object input) {
            return true;
          }
        });

        for (Field fff : ff) {
          System.out.println(fff.toString());
        }
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }

    }
    System.out.println("--------------");
    System.out.println("--------------");
    System.out.println("--------------");
    return new Node();

  }
}
