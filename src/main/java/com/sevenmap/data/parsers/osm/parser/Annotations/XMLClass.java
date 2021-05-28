package com.sevenmap.data.parsers.osm.parser.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface XMLClass {
  String key() default "";

  String id() default "";

  boolean isXMLAttribute() default true;
}
