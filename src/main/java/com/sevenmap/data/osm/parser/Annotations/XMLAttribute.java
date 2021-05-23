package com.sevenmap.data.osm.parser.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface XMLAttribute {
  String name() default "";

  boolean unique() default false;

  Class<?> idType() default Object.class;

  Class<?> valueType() default Object.class;
}
