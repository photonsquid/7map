package com.sevenmap.data.osm;

import java.util.Objects;

public class Way implements hasTags, hasNodes {
  private Integer id;
  private Metadata met;
  private Nodes nodes;
  private Tags tags;

  public Way() {
  }

  public Way(Integer id, Metadata met, Nodes nodes) {
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

  public Nodes getNodes() {
    return this.nodes;
  }

  public void setNodes(Nodes nodes) {
    this.nodes = nodes;
  }

  @Override
  public boolean addNode(Node nd) {
    return this.nodes.addNode(nd);
  }

  @Override
  public Node getNodeById(Integer id) {
    return this.nodes.getNodeById(id);
  }

  public Way id(Integer id) {
    setId(id);
    return this;
  }

  public Way met(Metadata met) {
    setMet(met);
    return this;
  }

  public Way nodes(Nodes nodes) {
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

  @Override
  public boolean addTag(String key, String value) {
    return this.tags.addTag(key, value);
  }

  @Override

  public String getValueFromTag(String key) {
    return this.tags.getValueByKey(key);
  }

}
