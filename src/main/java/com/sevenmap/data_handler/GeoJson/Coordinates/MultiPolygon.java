package com.sevenmap.data_handler.GeoJson.Coordinates;

import java.util.ArrayList;
import java.util.Objects;

public class MultiPolygon extends ArrayList<Polygon> {
  
  private ArrayList<Polygon> polygons;


  public MultiPolygon() {
  }

  public MultiPolygon(ArrayList<Polygon> polygons) {
    this.polygons = polygons;
  }

  public ArrayList<Polygon> getPolygons() {
    return this.polygons;
  }

  public void setPolygons(ArrayList<Polygon> polygons) {
    this.polygons = polygons;
  }

  public MultiPolygon polygons(ArrayList<Polygon> polygons) {
    setPolygons(polygons);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof MultiPolygon)) {
            return false;
        }
        MultiPolygon multiPolygon = (MultiPolygon) o;
        return Objects.equals(polygons, multiPolygon.polygons);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(polygons);
  }

  @Override
  public String toString() {
    return "{" +
      " polygons='" + getPolygons() + "'" +
      "}";
  }

  
}
