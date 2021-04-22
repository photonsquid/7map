package com.sevenmap.data_handler.GeoJson.Coordinates;

import java.util.ArrayList;
import java.util.Objects;

import com.sevenmap.data_handler.GeoJson.Type;

public class Polygon extends Coordinates {
  private ArrayList<LineString> linestrings;


  public Polygon() {
  }

  public Polygon(ArrayList<LineString> linestrings) {
    this.linestrings = linestrings;
  }

  public Type getType(){
    return new Type("Polygon");
  }
  
  public ArrayList<LineString> getLinestrings() {
    return this.linestrings;
  }

  public void setLinestrings(ArrayList<LineString> linestrings) {
    this.linestrings = linestrings;
  }

  public Polygon linestrings(ArrayList<LineString> linestrings) {
    setLinestrings(linestrings);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Polygon)) {
            return false;
        }
        Polygon polygon = (Polygon) o;
        return Objects.equals(linestrings, polygon.linestrings);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(linestrings);
  }

  @Override
  public String toString() {
    return "{" +
      " linestrings='" + getLinestrings() + "'" +
      "}";
  }
  

}
