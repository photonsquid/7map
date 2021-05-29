package com.sevenmap.data.parsers.osm.Structure.Bounds;

import java.util.Objects;

import com.sevenmap.data.parsers.osm.Annotations.XMLAttribute;

public class Bounds {

  @XMLAttribute
  private Double minlat;
  @XMLAttribute
  private Double maxlat;
  @XMLAttribute
  private Double minlon;
  @XMLAttribute
  private Double maxlon;

  public Bounds() {
  }

  public Bounds(Double minlat, Double maxlat, Double minlon, Double maxlon) {
    this.minlat = minlat;
    this.maxlat = maxlat;
    this.minlon = minlon;
    this.maxlon = maxlon;
  }

  public Double getMinlat() {
    return this.minlat;
  }

  public void setMinlat(Double minlat) {
    this.minlat = minlat;
  }

  public Double getMaxlat() {
    return this.maxlat;
  }

  public void setMaxlat(Double maxlat) {
    this.maxlat = maxlat;
  }

  public Double getMinlon() {
    return this.minlon;
  }

  public void setMinlon(Double minlon) {
    this.minlon = minlon;
  }

  public Double getMaxlon() {
    return this.maxlon;
  }

  public void setMaxlon(Double maxlon) {
    this.maxlon = maxlon;
  }

  public Bounds minlat(Double minlat) {
    setMinlat(minlat);
    return this;
  }

  public Bounds maxlat(Double maxlat) {
    setMaxlat(maxlat);
    return this;
  }

  public Bounds minlon(Double minlon) {
    setMinlon(minlon);
    return this;
  }

  public Bounds maxlon(Double maxlon) {
    setMaxlon(maxlon);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Bounds)) {
      return false;
    }
    Bounds bounds = (Bounds) o;
    return Objects.equals(minlat, bounds.minlat) && Objects.equals(maxlat, bounds.maxlat)
        && Objects.equals(minlon, bounds.minlon) && Objects.equals(maxlon, bounds.maxlon);
  }

  @Override
  public int hashCode() {
    return Objects.hash(minlat, maxlat, minlon, maxlon);
  }

  @Override
  public String toString() {
    return "{" + " minlat='" + getMinlat() + "'" + ", maxlat='" + getMaxlat() + "'" + ", minlon='" + getMinlon() + "'"
        + ", maxlon='" + getMaxlon() + "'" + "}";
  }

}
