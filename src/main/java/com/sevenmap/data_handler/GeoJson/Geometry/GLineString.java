package com.sevenmap.data_handler.GeoJson.Geometry;

import java.util.Objects;

import com.sevenmap.data_handler.GeoJson.Coordinates.CLineString;

public class GLineString extends Geometry {
  private CLineString coordinates;

  public GLineString() {
  }

  public GLineString(CLineString coordinates) {
    this.coordinates = coordinates;
  }

  public CLineString getCoordinates() {
    return this.coordinates;
  }

  public void setCoordinates(CLineString coordinates) {
    this.coordinates = coordinates;
  }

  public GLineString coordinates(CLineString coordinates) {
    setCoordinates(coordinates);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GLineString)) {
            return false;
        }
        GLineString gLineString = (GLineString) o;
        return Objects.equals(coordinates, gLineString.coordinates);
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
