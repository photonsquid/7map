package com.sevenmap.data_handler.GeoJson.Geometry;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.sevenmap.data_handler.GeoJson.Coordinates.Coordinates;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, 
      include = As.PROPERTY, property = "type") @JsonSubTypes({
         
      @JsonSubTypes.Type(value = GPoint.class, name = "Point"),
      @JsonSubTypes.Type(value = GMultiPolygon.class, name = "MultiPolygon"),
      @JsonSubTypes.Type(value = GPolygon.class, name = "Polygon")
   })

public abstract class Geometry {
   public abstract Coordinates getCoordinates();
}
