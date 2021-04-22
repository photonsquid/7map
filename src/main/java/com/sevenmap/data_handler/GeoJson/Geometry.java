package com.sevenmap.data_handler.GeoJson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Geometry {
  private Type type;
  private List<ArrayList<ArrayList<ArrayList<Float>>>> coordinates;


  public Geometry() {
  }

  public Geometry(Type type, List<ArrayList<ArrayList<ArrayList<Float>>>> coordinates) {
    this.type = type;
    this.coordinates = coordinates;
  }

  public Type getType() {
    return this.type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public List<ArrayList<ArrayList<ArrayList<Float>>>> getCoordinates() {
    return this.coordinates;
  }

  public void setCoordinates(List<ArrayList<ArrayList<ArrayList<Float>>>> coordinates) {
    this.coordinates = coordinates;
  }

  public Geometry type(Type type) {
    setType(type);
    return this;
  }

  public Geometry coordinates(List<ArrayList<ArrayList<ArrayList<Float>>>> coordinates) {
    setCoordinates(coordinates);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Geometry)) {
            return false;
        }
        Geometry geometry = (Geometry) o;
        return Objects.equals(type, geometry.type) && Objects.equals(coordinates, geometry.coordinates);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, coordinates);
  }

  @Override
  public String toString() {
    return "{" +
      " type='" + getType() + "'" +
      ", coordinates='" + getCoordinates() + "'" +
      "}";
  }

}
