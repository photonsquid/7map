package com.sevenmap.data.styles;

import java.util.ArrayList;

public class Styles extends ArrayList<AssetStyle> {

  public Styles() {
  }

  public AssetStyle findStyle(String tag) {
    for (AssetStyle as : this) {
      if (as.getV().equals(tag)) {
        return as;
      }
    }
    return null;
  }

}
