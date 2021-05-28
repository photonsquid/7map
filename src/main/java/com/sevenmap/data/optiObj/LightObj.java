package com.sevenmap.data.optiObj;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.sevenmap.core.Props;
import com.sevenmap.data.parsers.json.JsonParser;
import com.sevenmap.data.parsers.osm.OSM;
import com.sevenmap.data.parsers.osm.Elements.Root;
import com.sevenmap.data.parsers.osm.Elements.Node.Node;
import com.sevenmap.data.parsers.osm.Elements.Relation.Relation;
import com.sevenmap.data.parsers.osm.Elements.Way.Way;
import com.sevenmap.data.styles.Styles;

public class LightObj {
  private OSM osmMap;
  private Props props;
  private HashMap<Long, Relation> rels;
  private HashMap<Long, Way> ways;
  private HashMap<Long, Node> nodes;

  /**
   * Create an object ready to be optimized
   */
  public LightObj() {
  }

  /**
   * Create an object ready to be optimized
   * 
   * @param osmMap an 'non-optimized' OSM map
   * @param props  genral properties of the App (settings files, ...)
   */
  public LightObj(OSM osmMap, Props props) {
    this.osmMap = osmMap;
    this.props = props;
  }

  /**
   * Convert a list of nodes to a list of feature quickly displayable
   */
  public void parse() {

    // On définit les nouvelles coordonnées de chaque noeud.
    Root rt = osmMap.getRt();

    rels = rt.getRelations();
    ways = rt.getWays();
    nodes = rt.getNodes();

    // Parse styles.json
    Styles styles = JsonParser.parse(props.getSettingFile(), Styles.class);

    // 1. iterate on all relations and build them
    buildRelations(rt);
    // Construire =
    // Convert points from lat long to (x, y, z)
    // Parse styles.json and apply it to each objects

    // 1. Itérer sur toutes relations et les construire :
    // 1.1 construire les routes
    // 1.2 si les routes ou points utilisés n'ont pas d'autres références, ils
    // doivent être supprimés du root pour ne pas être rendu deux fois.
    // 2. Itérer sur toutes les routes restantes et les construire, en supprimant
    // les bons noeuds
    // 3. Itérer sur tous les noeuds restant et les construire
    //

  }

  /** Store optimized map to database */
  public void store() {
    // Store obj to database

  }

  private void buildRelations(Root rt) {
    for (Map.Entry<Long, Relation> entry : rels.entrySet()) {
      Long key = entry.getKey();
      Relation rel = entry.getValue();
      System.out.println(key.toString());
    }

  }

  private void buildWays() {

  }

  private void buildNodes() {

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
