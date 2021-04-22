package com.sevenmap.data_handler.GeoJson.Coordinates;

import java.util.ArrayList;
import java.util.Objects;

import com.sevenmap.data_handler.GeoJson.Type;

public class CMultiPolygon extends Coordinates {
  
  private ArrayList<Polygon> polygons;

  public CMultiPolygon() {
  }

  public Type getType(){
    return new Type("MultiPolygon");
  }

  public CMultiPolygon(ArrayList<Polygon> polygons) {
    this.polygons = polygons;
  }

  public ArrayList<Polygon> getPolygons() {
    return this.polygons;
  }

  public void setPolygons(ArrayList<Polygon> polygons) {
    this.polygons = polygons;
  }

  public CMultiPolygon polygons(ArrayList<Polygon> polygons) {
    setPolygons(polygons);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CMultiPolygon)) {
            return false;
        }
        CMultiPolygon multiPolygon = (CMultiPolygon) o;
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
