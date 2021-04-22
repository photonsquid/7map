package com.sevenmap.data_handler.GeoJson.Geometry;

import java.util.ArrayList;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("MultiPolygon")
public class MultiPolygon extends Geometry {
  @JsonProperty("coordinates")
  private ArrayList<Polygon> coordinates;

  public MultiPolygon() {
  }

  /**
   * Create a multi polygon from a list of several polygons
   * @param polygons list of polygons
   */
  public MultiPolygon(ArrayList<Polygon> coordinates) {
      this.coordinates = coordinates;
  }

  

  public ArrayList<Polygon> getPolygons() {
    return this.coordinates;
  }

  public void setPolygons(ArrayList<Polygon> polygons) {
    this.coordinates = polygons;
  }

  public MultiPolygon polygons(ArrayList<Polygon> polygons) {
    setPolygons(polygons);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof MultiPolygon)) {
            return false;
        }
        MultiPolygon multiPolygon = (MultiPolygon) o;
        return Objects.equals(coordinates, multiPolygon.coordinates);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(coordinates);
  }

  @Override
  public String toString() {
    return "{" +
      " polygons='" + getPolygons() + "'" +
      "}";
  }

  
}
