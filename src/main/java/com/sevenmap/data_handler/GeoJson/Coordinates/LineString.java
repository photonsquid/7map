package com.sevenmap.data_handler.GeoJson.Coordinates;

import java.util.ArrayList;
import java.util.Objects;

public class LineString extends ArrayList<Point> {
  private ArrayList<Point> points;

  public LineString() {
  }

  public LineString(ArrayList<Point> points) {
    this.points = points;
  }

  public ArrayList<Point> getPoints() {
    return this.points;
  }

  public void setPoints(ArrayList<Point> points) {
    this.points = points;
  }

  public LineString points(ArrayList<Point> points) {
    setPoints(points);
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
        return Objects.equals(points, lineString.points);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(points);
  }

  @Override
  public String toString() {
    return "{" +
      " points='" + getPoints() + "'" +
      "}";
  }


}
