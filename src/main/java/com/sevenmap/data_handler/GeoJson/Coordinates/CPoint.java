package com.sevenmap.data_handler.GeoJson.Coordinates;

import java.util.ArrayList;
import java.util.Objects;

import com.sevenmap.data_handler.GeoJson.Type;

public class CPoint extends Coordinates {
  private ArrayList<Double> coordinates;


  public CPoint() {
    this.coordinates = new ArrayList<Double>();
    System.out.println("On crée un point vide.");
  }

  public CPoint(ArrayList<Double> obj) {
    this.coordinates = new ArrayList<Double>();
    try {
      System.out.println("On crée un point non vide");
      for (Double cc : obj) {
        coordinates.add(cc);
      }
    } catch (Exception e) {
      //TODO: handle exception
    }
  }

  @Override
  public boolean add(Object obj){
    System.out.print("on ajoute un point (pas attendu)...");
    return true;
  }

  public Type getType(){
    return new Type("Point");
  }
  
  public ArrayList<Double> getCoordinates() {
    return this.coordinates;
  }

  public void setCoordinates(ArrayList<Double> coordinates) {
    this.coordinates = coordinates;
  }

  public CPoint coordinates(ArrayList<Double> coordinates) {
    setCoordinates(coordinates);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CPoint)) {
            return false;
        }
        CPoint point = (CPoint) o;
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
