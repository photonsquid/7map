package com.sevenmap.data.osm.Elements.Way;

import java.util.HashMap;
import java.util.Objects;

import com.sevenmap.data.osm.Elements.Metadata.Metadata;
import com.sevenmap.data.osm.Elements.Node.Node;
import com.sevenmap.data.osm.Elements.Tag.Tag;
import com.sevenmap.data.osm.parser.Annotations.XMLClass;

@XMLClass(key = "way", id = "id")
public class Way {
  private Integer id;
  private Metadata met;
  private HashMap<Integer, Node> nodes;
  private Tag tags;

  public Way() {
  }

  public Way(Integer id, Metadata met, HashMap<Integer, Node> nodes) {
    this.id = id;
    this.met = met;
    this.nodes = nodes;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Metadata getMet() {
    return this.met;
  }

  public void setMet(Metadata met) {
    this.met = met;
  }

  public HashMap<Integer, Node> getNodes() {
    return this.nodes;
  }

  public void setNodes(HashMap<Integer, Node> nodes) {
    this.nodes = nodes;
  }

  public Way id(Integer id) {
    setId(id);
    return this;
  }

  public Way met(Metadata met) {
    setMet(met);
    return this;
  }

  public Way nodes(HashMap<Integer, Node> nodes) {
    setNodes(nodes);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Way)) {
      return false;
    }
    Way way = (Way) o;
    return Objects.equals(id, way.id) && Objects.equals(met, way.met) && Objects.equals(nodes, way.nodes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, met, nodes);
  }

  @Override
  public String toString() {
    return "{" + " id='" + getId() + "'" + ", met='" + getMet() + "'" + ", nodes='" + getNodes() + "'" + "}";
  }

}
