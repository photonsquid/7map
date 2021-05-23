package com.sevenmap.data_handler.GeoJson.Geometry;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.sevenmap.data_handler.GeoJson.Coordinates.CPolygon;

@JsonTypeName("Polygon")
public class GPolygon extends Geometry {
  private CPolygon coordinates;


  public GPolygon() {
  }

  public GPolygon(CPolygon coordinates) {
    this.coordinates = coordinates;
  }

  public CPolygon getCoordinates() {
    return this.coordinates;
  }

  public void setCoordinates(CPolygon coordinates) {
    this.coordinates = coordinates;
  }

  public GPolygon coordinates(CPolygon coordinates) {
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
