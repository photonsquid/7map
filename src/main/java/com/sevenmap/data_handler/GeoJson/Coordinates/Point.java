package com.sevenmap.data_handler.GeoJson.Coordinates;

import java.util.ArrayList;
import java.util.Objects;

import com.sevenmap.data_handler.GeoJson.Type;

public class Point extends Coordinates {
  private ArrayList<Float> coordinates;


  public Point() {
  }

  public Point(ArrayList<Float> coordinates) {
    this.coordinates = coordinates;
  }

  public Type getType(){
    return new Type("Point");
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
