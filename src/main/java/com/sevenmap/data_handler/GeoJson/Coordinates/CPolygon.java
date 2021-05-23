package com.sevenmap.data_handler.GeoJson.Coordinates;

import java.util.ArrayList;
import java.util.Objects;

public class CPolygon extends Coordinates {
  private ArrayList<ArrayList<CLineString>> polygons;


  public CPolygon() {
    this.polygons = new ArrayList<ArrayList<CLineString>>();
    System.out.println("On crée une liste de polygones vide.");
  }

  // public Polygon(ArrayList<CLineString> linestrings) {
  //   this.linestrings = linestrings;
  // }
  

  @Override
  public boolean add(Object obj){
    try {
      
      System.out.println("On ajoute un Polygone à la liste.");
      ArrayList<CLineString> poly = new ArrayList<CLineString>();
      ArrayList<?> bb = (ArrayList<?>) obj;
        System.out.println("Cast ?");
      for (Object arrayList : bb) {
        System.out.println("Arraylist  vide ?");
        System.out.println(arrayList);
        CLineString ls = new CLineString(arrayList);
        poly.add(ls);
      }
      polygons.add(poly);
      System.out.println("Etat de polygons :");
      System.out.println(polygons);
    } catch (Exception e) {
      //TODO: handle exception
    }
    System.out.println("Ajout d'un Polygone");
    // System.out.println(tt.toString());
    return true;
  }

  public CPolygon(ArrayList<ArrayList<CLineString>> polygons) {
    this.polygons = polygons;
  }

  public ArrayList<ArrayList<CLineString>> getPolygons() {
    return this.polygons;
  }

  public void setPolygons(ArrayList<ArrayList<CLineString>> polygons) {
    this.polygons = polygons;
  }

  public CPolygon polygons(ArrayList<ArrayList<CLineString>> polygons) {
    setPolygons(polygons);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CPolygon)) {
            return false;
        }
        CPolygon cPolygon = (CPolygon) o;
        return Objects.equals(polygons, cPolygon.polygons);
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
