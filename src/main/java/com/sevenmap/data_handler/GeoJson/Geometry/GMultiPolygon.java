package com.sevenmap.data_handler.GeoJson.Geometry;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.sevenmap.data_handler.GeoJson.Coordinates.CPolygon;

@JsonTypeName("MultiPolygon")
public class GMultiPolygon extends Geometry {
  @JsonProperty("coordinates")
  private CPolygon[] coordinates;


  public GMultiPolygon() {
  }

  public GMultiPolygon(CPolygon[] coordinates) {
    this.coordinates = coordinates;
  }

  public CPolygon[] getCoordinates() {
    return this.coordinates;
  }

  public void setCoordinates(CPolygon[] coordinates) {
    this.coordinates = coordinates;
  }

  public GMultiPolygon coordinates(CPolygon[] coordinates) {
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
