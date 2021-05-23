package com.sevenmap.data.optiObj;

import java.util.Objects;

import com.sevenmap.data.osm.api.OSMAPI;

public class LightObj {
  private OSMAPI osmMap;

  public LightObj() {
  }

  public LightObj(OSMAPI osmMap) {
    this.osmMap = osmMap;
  }

  public void convertObj() {
    // Convert points from lat long to (x, y, z)
    // Convert a list of nodes to a list of feature quickly displayable
    // Parse styles.json and apply it to each objects

  }

  public void storeObj() {
    // Store obj to database

  }

  public OSMAPI getOsmMap() {
    return this.osmMap;
  }

  public void setOsmMap(OSMAPI osmMap) {
    this.osmMap = osmMap;
  }

  public LightObj osmMap(OSMAPI osmMap) {
    setOsmMap(osmMap);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof LightObj)) {
      return false;
    }
    LightObj lightObj = (LightObj) o;
    return Objects.equals(osmMap, lightObj.osmMap);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(osmMap);
  }

  @Override
  public String toString() {
    return "{" + " osmMap='" + getOsmMap() + "'" + "}";
  }

}
