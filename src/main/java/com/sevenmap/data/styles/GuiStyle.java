package com.sevenmap.data.styles;

import java.util.Objects;

public class GuiStyle {
  private String backgroundColor;
  private String mainColor;

  public GuiStyle() {
  }

  public GuiStyle(String backgroundColor, String mainColor) {
    this.backgroundColor = backgroundColor;
    this.mainColor = mainColor;
  }

  public String getBackgroundColor() {
    return this.backgroundColor;
  }

  public void setBackgroundColor(String backgroundColor) {
    this.backgroundColor = backgroundColor;
  }

  public String getMainColor() {
    return this.mainColor;
  }

  public void setMainColor(String mainColor) {
    this.mainColor = mainColor;
  }

  public GuiStyle backgroundColor(String backgroundColor) {
    setBackgroundColor(backgroundColor);
    return this;
  }

  public GuiStyle mainColor(String mainColor) {
    setMainColor(mainColor);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof GuiStyle)) {
      return false;
    }
    GuiStyle guiStyle = (GuiStyle) o;
    return Objects.equals(backgroundColor, guiStyle.backgroundColor) && Objects.equals(mainColor, guiStyle.mainColor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(backgroundColor, mainColor);
  }

  @Override
  public String toString() {
    return "{" + " backgroundColor='" + getBackgroundColor() + "'" + ", mainColor='" + getMainColor() + "'" + "}";
  }

}
