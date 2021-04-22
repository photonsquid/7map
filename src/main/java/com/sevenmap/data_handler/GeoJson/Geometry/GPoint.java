package com.sevenmap.data_handler.GeoJson.Geometry;


import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.sevenmap.data_handler.GeoJson.Coordinates.Point;

@JsonTypeName("Point")
public class GPoint extends Geometry {
  private Point coordinates;



  public GPoint() {
  }

  public GPoint(Point coordinates) {
    this.coordinates = coordinates;
  }

  public Point getCoordinates() {
    return this.coordinates;
  }

  public void setCoordinates(Point coordinates) {
    this.coordinates = coordinates;
  }

  public GPoint coordinates(Point coordinates) {
    setCoordinates(coordinates);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GPoint)) {
            return false;
        }
        GPoint gPoint = (GPoint) o;
        return Objects.equals(coordinates, gPoint.coordinates);
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
