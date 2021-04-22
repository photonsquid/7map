package com.sevenmap.data_handler.GeoJson.Geometry;

import java.util.Objects;

import com.sevenmap.data_handler.GeoJson.Coordinates.LineString;

public class GLineString extends Geometry {
  private LineString coordinates;

  public GLineString() {
  }

  public GLineString(LineString coordinates) {
    this.coordinates = coordinates;
  }

  public LineString getCoordinates() {
    return this.coordinates;
  }

  public void setCoordinates(LineString coordinates) {
    this.coordinates = coordinates;
  }

  public GLineString coordinates(LineString coordinates) {
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
