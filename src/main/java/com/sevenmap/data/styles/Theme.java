package com.sevenmap.data.styles;

import java.util.ArrayList;
import java.util.Objects;

public class Theme {
  private String name;
  private ArrayList<AssetStyle> data;
  private GuiStyle gui;

  public Theme() {
  }

  public Theme(String name, ArrayList<AssetStyle> data, GuiStyle gui) {
    this.name = name;
    this.data = data;
    this.gui = gui;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ArrayList<AssetStyle> getData() {
    return this.data;
  }

  public void setData(ArrayList<AssetStyle> data) {
    this.data = data;
  }

  public GuiStyle getGui() {
    return this.gui;
  }

  public void setGui(GuiStyle gui) {
    this.gui = gui;
  }

  public Theme name(String name) {
    setName(name);
    return this;
  }

  public Theme data(ArrayList<AssetStyle> data) {
    setData(data);
    return this;
  }

  public Theme gui(GuiStyle gui) {
    setGui(gui);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Theme)) {
      return false;
    }
    Theme theme = (Theme) o;
    return Objects.equals(name, theme.name) && Objects.equals(data, theme.data) && Objects.equals(gui, theme.gui);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, data, gui);
  }

  @Override
  public String toString() {
    return "{" + " name='" + getName() + "'" + ", data='" + getData() + "'" + ", gui='" + getGui() + "'" + "}";
  }

}
