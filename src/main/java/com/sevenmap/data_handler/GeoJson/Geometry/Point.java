package com.sevenmap.data_handler.GeoJson.Geometry;

import java.util.ArrayList;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Point")
public class Point extends ArrayList<Float> {
  private ArrayList<Float> coordinates;


  public Point() {
  }

  public Point(ArrayList<Float> coordinates) {
    this.coordinates = coordinates;
  }

  public ArrayList<Float> getCoordinates() {
    return this.coordinates;
  }

  public void setCoordinates(ArrayList<Float> coordinates) {
    this.coordinates = coordinates;
  }

  public Point coordinates(ArrayList<Float> coordinates) {
    setCoordinates(coordinates);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Point)) {
            return false;
        }
        Point point = (Point) o;
        return Objects.equals(coordinates, point.coordinates);
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
