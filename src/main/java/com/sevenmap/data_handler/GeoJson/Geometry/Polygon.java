package com.sevenmap.data_handler.GeoJson.Geometry;

import java.util.ArrayList;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Polygon")
public class Polygon extends ArrayList<LineString> {
  private ArrayList<LineString> coordinates;

  public Polygon() {
  }

  public Polygon(ArrayList<LineString> coordinates) {
    this.coordinates = coordinates;
  }

  public ArrayList<LineString> getCoordinates() {
    return this.coordinates;
  }

  public void setCoordinates(ArrayList<LineString> coordinates) {
    this.coordinates = coordinates;
  }

  public Polygon coordinates(ArrayList<LineString> coordinates) {
    setCoordinates(coordinates);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Polygon)) {
            return false;
        }
        Polygon polygon = (Polygon) o;
        return Objects.equals(coordinates, polygon.coordinates);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(coordinates);
  }

  @Override
  public String toString() {
    return "{" +
      " coordinates='" + getCoordinates() + "'" +
      "}";
  }

}
