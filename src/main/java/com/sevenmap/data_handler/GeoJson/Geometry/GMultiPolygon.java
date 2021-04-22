package com.sevenmap.data_handler.GeoJson.Geometry;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.sevenmap.data_handler.GeoJson.Coordinates.CMultiPolygon;

@JsonTypeName("MultiPolygon")
public class GMultiPolygon extends Geometry {
  @JsonProperty("coordinates")
  private CMultiPolygon coordinates;


  public GMultiPolygon() {
  }

  public GMultiPolygon(CMultiPolygon coordinates) {
    this.coordinates = coordinates;
  }

  public CMultiPolygon getCoordinates() {
    return this.coordinates;
  }

  public void setCoordinates(CMultiPolygon coordinates) {
    this.coordinates = coordinates;
  }

  public GMultiPolygon coordinates(CMultiPolygon coordinates) {
    setCoordinates(coordinates);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GMultiPolygon)) {
            return false;
        }
        GMultiPolygon gMultiPolygon = (GMultiPolygon) o;
        return Objects.equals(coordinates, gMultiPolygon.coordinates);
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
