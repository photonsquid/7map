package com.sevenmap.data.styles;

import java.util.Objects;

public class AssetStyle {
  private Integer k;
  private Integer v;
  private Integer borderThickness;
  private Integer borderColor;
  private Integer color;

  public AssetStyle() {
  }

  public AssetStyle(Integer k, Integer v, Integer borderThickness, Integer borderColor, Integer color) {
    this.k = k;
    this.v = v;
    this.borderThickness = borderThickness;
    this.borderColor = borderColor;
    this.color = color;
  }

  public Integer getK() {
    return this.k;
  }

  public void setK(Integer k) {
    this.k = k;
  }

  public Integer getV() {
    return this.v;
  }

  public void setV(Integer v) {
    this.v = v;
  }

  public Integer getBorderThickness() {
    return this.borderThickness;
  }

  public void setBorderThickness(Integer borderThickness) {
    this.borderThickness = borderThickness;
  }

  public Integer getBorderColor() {
    return this.borderColor;
  }

  public void setBorderColor(Integer borderColor) {
    this.borderColor = borderColor;
  }

  public Integer getColor() {
    return this.color;
  }

  public void setColor(Integer color) {
    this.color = color;
  }

  public AssetStyle k(Integer k) {
    setK(k);
    return this;
  }

  public AssetStyle v(Integer v) {
    setV(v);
    return this;
  }

  public AssetStyle borderThickness(Integer borderThickness) {
    setBorderThickness(borderThickness);
    return this;
  }

  public AssetStyle borderColor(Integer borderColor) {
    setBorderColor(borderColor);
    return this;
  }

  public AssetStyle color(Integer color) {
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
