package com.sevenmap.data.styles;

import java.util.ArrayList;
import java.util.Objects;

public class Styles {
  private ArrayList<Theme> themes;

  public Styles() {
  }

  public Styles(ArrayList<Theme> themes) {
    this.themes = themes;
  }

  public AssetStyle findStyle(String type, String themeName) {
    for (Theme theme : themes) {
      if (theme.getName().equals(themeName)) {
        for (AssetStyle data : theme.getData()) {
          if (data.getV().equals(type)) {
            return data;
          }
        }
      }
    }
    return null;
  }

  public GuiStyle findGuiStyle(String themeName) {
    for (Theme theme : themes) {
      if (theme.getName().equals(themeName)) {
        return theme.getGui();
      }
    }
    return null;
  }

  public ArrayList<Theme> getThemes() {
    return this.themes;
  }

  public void setThemes(ArrayList<Theme> themes) {
    this.themes = themes;
  }

  public Styles themes(ArrayList<Theme> themes) {
    setThemes(themes);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Styles)) {
      return false;
    }
    Styles styles = (Styles) o;
    return Objects.equals(themes, styles.themes);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(themes);
  }

  @Override
  public String toString() {
    return "{" + " themes='" + getThemes() + "'" + "}";
  }

}
