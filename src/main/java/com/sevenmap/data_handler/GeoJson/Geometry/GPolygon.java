package com.sevenmap.data_handler.GeoJson.Geometry;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.sevenmap.data_handler.GeoJson.Coordinates.Polygon;

@JsonTypeName("Polygon")
public class GPolygon extends Geometry {
  private Polygon coordinates;


  public GPolygon() {
  }

  public GPolygon(Polygon coordinates) {
    this.coordinates = coordinates;
  }

  public Polygon getCoordinates() {
    return this.coordinates;
  }

  public void setCoordinates(Polygon coordinates) {
    this.coordinates = coordinates;
  }

  public GPolygon coordinates(Polygon coordinates) {
    setCoordinates(coordinates);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GPolygon)) {
            return false;
        }
        GPolygon gPolygon = (GPolygon) o;
        return Objects.equals(coordinates, gPolygon.coordinates);
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
