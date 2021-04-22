package com.sevenmap.data_handler.GeoJson.Geometry;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, 
      include = As.PROPERTY, property = "type") @JsonSubTypes({
         
      @JsonSubTypes.Type(value = Point.class, name = "Point"),
      @JsonSubTypes.Type(value = MultiPolygon.class, name = "MultiPolygon"),
      @JsonSubTypes.Type(value = Polygon.class, name = "Polygon")
   })

public abstract class Geometry {
  
}
