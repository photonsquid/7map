package com.sevenmap.data.osm;

import java.util.HashMap;

public class Tags implements hasTags {
  private HashMap<String, String> tags;

  public Tags() {
    tags = new HashMap<String, String>();
  }

  public Tags(HashMap<String, String> tags) {
    this.tags = tags;
  }

  public Tags(String key, String value) {
    this();
    this.addTag(key, value);
  }

  @Override
  public boolean addTag(String key, String value) {
    if (this.tags.put(key, value) == null) {
      return false;
    }
    return true;
  }

  public String getValueByKey(String key) {
    return this.tags.get(key);
  }

  @Override
  public String getValueFromTag(String key) {
    return this.getValueByKey(key);
  }

}
