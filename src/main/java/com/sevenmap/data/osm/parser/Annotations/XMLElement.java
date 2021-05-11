package com.sevenmap.data.osm.parser.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface XMLElement {
  String tag() default "";

  Class<?> keyType() default Integer.class;

  Class<?> valueType() default String.class;
}
