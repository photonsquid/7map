package com.sevenmap.data.osm;

import java.util.HashMap;
import java.util.Objects;

public class Nodes implements hasNodes {
  private HashMap<Integer, Node> nodes;

  public Nodes() {
    nodes = new HashMap<Integer, Node>();
  }

  public Nodes(HashMap<Integer, Node> nodes) {
    this.nodes = nodes;
  }

  public HashMap<Integer, Node> getNodes() {
    return this.nodes;
  }

  public void setNodes(HashMap<Integer, Node> nodes) {
    this.nodes = nodes;
  }

  public Nodes nodes(HashMap<Integer, Node> nodes) {
    setNodes(nodes);
    return this;
  }

  public boolean addNode(Node nd) {
    if (nodes.put(nd.getId(), nd) == null) {
      return false;
    }
    return true;
  }

  public Node getNodeById(Integer id) {
    return nodes.get(id);
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Nodes)) {
      return false;
    }
    Nodes nodes = (Nodes) o;
    return Objects.equals(nodes, nodes.nodes);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(nodes);
  }

  @Override
  public String toString() {
    return "{" + " nodes='" + getNodes() + "'" + "}";
  }

}
