package com.sevenmap.data.styles;

import java.util.ArrayList;
import java.util.Objects;

public class Styles extends ArrayList<Styles> {
  private AssetStyle stlyes;

  public Styles() {
  }

  public Styles(AssetStyle stlyes) {
    this.stlyes = stlyes;
  }

  public AssetStyle getStlyes() {
    return this.stlyes;
  }

  public void setStlyes(AssetStyle stlyes) {
    this.stlyes = stlyes;
  }

  public Styles stlyes(AssetStyle stlyes) {
    setStlyes(stlyes);
    return this;
  }

  public AssetStyle findStyle(String type) {
    // TODO
    return new AssetStyle();
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Styles)) {
      return false;
    }
    Styles styles = (Styles) o;
    return Objects.equals(stlyes, styles.stlyes);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(stlyes);
  }

  @Override
  public String toString() {
    return "{" + " stlyes='" + getStlyes() + "'" + "}";
  }

}
