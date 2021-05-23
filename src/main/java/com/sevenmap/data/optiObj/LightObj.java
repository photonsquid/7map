package com.sevenmap.data.optiObj;

import java.util.Objects;

import com.sevenmap.data.osm.OSM;

public class LightObj {
  private OSM osmMap;

  public LightObj() {
  }

  public LightObj(OSM osmMap) {
    this.osmMap = osmMap;
  }

  // Convert a list of nodes to a list of feature quickly displayable

  public void convertObj() {
    // Construire =
    // Convert points from lat long to (x, y, z)
    // Parse styles.json and apply it to each objects

    // 1. Itérer sur toutes relations et les construire :
    // 1.1 construire les routes
    // 1.2 si les routes ou points utilisés n'ont pas d'autres références, ils
    // doivent être supprimés du root pour ne pas être rendu deux fois.
    // 2. Itérer sur toutes les routes restantes et les construire
    // 3. Itérer sur tous les noeuds restant et les construire

  }

  public void storeObj() {
    // Store obj to database

  }

  public OSM getOsmMap() {
    return this.osmMap;
  }

  public void setOsmMap(OSM osmMap) {
    this.osmMap = osmMap;
  }

  public LightObj osmMap(OSM osmMap) {
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
