package com.sevenmap.data_handler.GeoJson.Coordinates;

import java.util.ArrayList;
import java.util.Objects;

public class CLineString extends Coordinates{
  private ArrayList<ArrayList<CPoint>> lignes;

  public CLineString() {
    System.out.println("On crée une ligne de Points vide.");
  }
public CLineString(Object obj) {
  this.lignes = new ArrayList<ArrayList<CPoint>>();
  System.out.println("On crée une ligne de Points non vide.");
  try {
    ArrayList<CPoint> ligne = new ArrayList<CPoint>();
    ArrayList<Double> bb = (ArrayList<Double>) obj;
    System.out.println("On a convertit en double.");
    CPoint pt = new CPoint(bb);
    ligne.add(pt);
    lignes.add(ligne);
  } catch (Exception e) {
    //TODO: handle exception
  }
}



  @Override
  public boolean add(Object obj){
    try {
      
      System.out.println("On ajoute un Point à la ligne.");
      ArrayList<CPoint> ligne = new ArrayList<CPoint>();
      ArrayList<Double> bb = (ArrayList<Double>) obj;
      CPoint pt = new CPoint(bb);
      ligne.add(pt);
      lignes.add(ligne);
    } catch (Exception e) {
      //TODO: handle exception
    }
    return true;
  }


  public CLineString(ArrayList<ArrayList<CPoint>> lignes) {
    this.lignes = lignes;
  }

  public ArrayList<ArrayList<CPoint>> getLignes() {
    return this.lignes;
  }

  public void setLignes(ArrayList<ArrayList<CPoint>> lignes) {
    this.lignes = lignes;
  }

  public CLineString lignes(ArrayList<ArrayList<CPoint>> lignes) {
    setLignes(lignes);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CLineString)) {
            return false;
        }
        CLineString cLineString = (CLineString) o;
        return Objects.equals(lignes, cLineString.lignes);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(lignes);
  }

  @Override
  public String toString() {
    return "{" +
      " lignes='" + getLignes() + "'" +
      "}";
  }

}
