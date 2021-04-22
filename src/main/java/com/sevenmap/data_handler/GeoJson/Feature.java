package com.sevenmap.data_handler.GeoJson;

import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sevenmap.data_handler.GeoJson.Geometry.Geometry;

public class Feature {
  @JsonProperty("type")
  private Type type;
  @JsonProperty("geometry")
  private Geometry geometry;
  
  // Let's ignore properties for now
  @JsonIgnore
  private Map<String, ?> properties;

  public Feature() {
  }

  public Feature(Type type, Geometry geometry, Map<String,?> properties) {
    this.type = type;
    this.geometry = geometry;
    this.properties = properties;
  }

  public Type getType() {
    return this.type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public Geometry getGeometry() {
    return this.geometry;
  }

  public void setGeometry(Geometry geometry) {
    this.geometry = geometry;
  }

  public Map<String,?> getProperties() {
    return this.properties;
  }

  public void setProperties(Map<String,?> properties) {
    this.properties = properties;
  }

  public Feature type(Type type) {
    setType(type);
    return this;
  }

  public Feature geometry(Geometry geometry) {
    setGeometry(geometry);
    return this;
  }

  public Feature properties(Map<String,?> properties) {
    setProperties(properties);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Feature)) {
            return false;
        }
        Feature feature = (Feature) o;
        return Objects.equals(type, feature.type) && Objects.equals(geometry, feature.geometry) && Objects.equals(properties, feature.properties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, geometry, properties);
  }

  @Override
  public String toString() {
    return "{" +
      " type='" + getType() + "'" +
      ", geometry='" + getGeometry() + "'" +
      ", properties='" + getProperties() + "'" +
      "}";
  }


}