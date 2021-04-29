package com.sevenmap.data.osm;

public interface hasTags {
  public boolean addTag(String key, String Value);

  public String getValueFromTag(String key);
}
