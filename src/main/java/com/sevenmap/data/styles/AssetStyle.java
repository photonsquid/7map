package com.sevenmap.data.styles;

import java.util.Objects;

public class AssetStyle {
  private String k;
  private String v;
  private Integer borderThickness;
  private String borderColor;
  private String color;

  public AssetStyle() {
  }

  public AssetStyle(String k, String v, Integer borderThickness, String borderColor, String color) {
    this.k = k;
    this.v = v;
    this.borderThickness = borderThickness;
    this.borderColor = borderColor;
    this.color = color;
  }

  public String getK() {
    return this.k;
  }

  public void setK(String k) {
    this.k = k;
  }

  public String getV() {
    return this.v;
  }

  public void setV(String v) {
    this.v = v;
  }

  public Integer getBorderThickness() {
    return this.borderThickness;
  }

  public void setBorderThickness(Integer borderThickness) {
    this.borderThickness = borderThickness;
  }

  public String getBorderColor() {
    return this.borderColor;
  }

  public void setBorderColor(String borderColor) {
    this.borderColor = borderColor;
  }

  public String getColor() {
    return this.color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public AssetStyle k(String k) {
    setK(k);
    return this;
  }

  public AssetStyle v(String v) {
    setV(v);
    return this;
  }

  public AssetStyle borderThickness(Integer borderThickness) {
    setBorderThickness(borderThickness);
    return this;
  }

  public AssetStyle borderColor(String borderColor) {
    setBorderColor(borderColor);
    return this;
  }

  public AssetStyle color(String color) {
    setColor(color);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof AssetStyle)) {
      return false;
    }
    AssetStyle assetStyle = (AssetStyle) o;
    return Objects.equals(k, assetStyle.k) && Objects.equals(v, assetStyle.v)
        && Objects.equals(borderThickness, assetStyle.borderThickness)
        && Objects.equals(borderColor, assetStyle.borderColor) && Objects.equals(color, assetStyle.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(k, v, borderThickness, borderColor, color);
  }

  @Override
  public String toString() {
    return "{" + " k='" + getK() + "'" + ", v='" + getV() + "'" + ", borderThickness='" + getBorderThickness() + "'"
        + ", borderColor='" + getBorderColor() + "'" + ", color='" + getColor() + "'" + "}";
  }

}
