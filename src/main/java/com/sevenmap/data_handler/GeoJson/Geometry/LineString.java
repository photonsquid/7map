package com.sevenmap.data_handler.GeoJson.Geometry;

import java.util.ArrayList;
import java.util.Objects;

public class LineString extends ArrayList<Point> {
  private ArrayList<LineString> coordinates;

  public LineString() {
  }

  public LineString(ArrayList<LineString> coordinates) {
    this.coordinates = coordinates;
  }

  public ArrayList<LineString> getCoordinates() {
    return this.coordinates;
  }

  public void setCoordinates(ArrayList<LineString> coordinates) {
    this.coordinates = coordinates;
  }

  public LineString coordinates(ArrayList<LineString> coordinates) {
    setCoordinates(coordinates);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof LineString)) {
            return false;
        }
        LineString lineString = (LineString) o;
        return Objects.equals(coordinates, lineString.coordinates);
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
