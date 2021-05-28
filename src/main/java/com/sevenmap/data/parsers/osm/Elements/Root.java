package com.sevenmap.data.parsers.osm.Elements;

import java.util.HashMap;
import java.util.Objects;

import com.sevenmap.data.parsers.osm.Elements.Bounds.Bounds;
import com.sevenmap.data.parsers.osm.Elements.Node.Node;
import com.sevenmap.data.parsers.osm.Elements.Relation.Relation;
import com.sevenmap.data.parsers.osm.Elements.Way.Way;
import com.sevenmap.data.parsers.osm.parser.Annotations.XMLAttribute;
import com.sevenmap.data.parsers.osm.parser.Annotations.XMLClass;
import com.sevenmap.data.parsers.osm.parser.Annotations.XMLElement;

@XMLClass(key = "osm")
public class Root {

  @XMLAttribute
  private String version;
  @XMLAttribute
  private String generator;

  @XMLElement
  private Bounds bounds;

  @XMLElement(tag = "node", valueType = Node.class)
  private HashMap<Long, Node> nodes;
  @XMLElement(tag = "way", valueType = Way.class)
  private HashMap<Long, Way> ways;
  @XMLElement(tag = "relation", valueType = Relation.class)
  private HashMap<Long, Relation> relations;

  public Root() {
  }

  public Root(String version, String generator, Bounds bounds, HashMap<Long, Node> nodes, HashMap<Long, Way> ways,
      HashMap<Long, Relation> relations) {
    this.version = version;
    this.generator = generator;
    this.bounds = bounds;
    this.nodes = nodes;
    this.ways = ways;
    this.relations = relations;
  }

  public String getVersion() {
    return this.version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getGenerator() {
    return this.generator;
  }

  public void setGenerator(String generator) {
    this.generator = generator;
  }

  public Bounds getBounds() {
    return this.bounds;
  }

  public void setBounds(Bounds bounds) {
    this.bounds = bounds;
  }

  public HashMap<Long, Node> getNodes() {
    return this.nodes;
  }

  public void setNodes(HashMap<Long, Node> nodes) {
    this.nodes = nodes;
  }

  public HashMap<Long, Way>

      getWays() {
    return this.ways;
  }

  public void setWays(HashMap<Long, Way> ways) {
    this.ways = ways;
  }

  public HashMap<Long, Relation>

      getRelations() {
    return this.relations;
  }

  public void setRelations(HashMap<Long, Relation> relations) {
    this.relations = relations;
  }

  public Root version(String version) {
    setVersion(version);
    return this;
  }

  public Root generator(String generator) {
    setGenerator(generator);
    return this;
  }

  public Root bounds(Bounds bounds) {
    setBounds(bounds);
    return this;
  }

  public Root nodes(HashMap<Long, Node> nodes) {
    setNodes(nodes);
    return this;
  }

  public Root ways(HashMap<Long, Way> ways) {
    setWays(ways);
    return this;
  }

  public Root relations(HashMap<Long, Relation> relations) {
    setRelations(relations);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Root)) {
      return false;
    }
    Root root = (Root) o;
    return Objects.equals(version, root.version) && Objects.equals(generator, root.generator)
        && Objects.equals(bounds, root.bounds) && Objects.equals(nodes, root.nodes) && Objects.equals(ways, root.ways)
        && Objects.equals(relations, root.relations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(version, generator, bounds, nodes, ways, relations);
  }

  @Override
  public String toString() {
    return "{" + " version='" + getVersion() + "'" + ", generator='" + getGenerator() + "'" + ", bounds='" + getBounds()
        + "'" + ", nodes='" + getNodes() + "'" + ", ways='" + getWays() + "'" + ", relations='" + getRelations() + "'"
        + "}";
  }

}
