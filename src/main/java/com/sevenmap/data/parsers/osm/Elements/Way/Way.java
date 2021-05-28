package com.sevenmap.data.parsers.osm.Elements.Way;

import java.util.ArrayList;
import java.util.Objects;

import com.sevenmap.data.parsers.osm.Elements.Metadata.Metadata;
import com.sevenmap.data.parsers.osm.Elements.Node.Nd;
import com.sevenmap.data.parsers.osm.Elements.Tag.Tag;
import com.sevenmap.data.parsers.osm.parser.Annotations.XMLAttribute;
import com.sevenmap.data.parsers.osm.parser.Annotations.XMLElement;

public class Way {

  @XMLAttribute(unique = true)
  private long id;
  @XMLAttribute
  private Metadata met;
  @XMLElement(tag = "nd", valueType = Nd.class)
  private ArrayList<Nd> nodes;
  @XMLElement(tag = "tag", valueType = Tag.class)
  private ArrayList<Tag> tags;

  public Way() {
  }

  public Way(long id, Metadata met, ArrayList<Nd> nodes, ArrayList<Tag> tags) {
    this.id = id;
    this.met = met;
    this.nodes = nodes;
    this.tags = tags;
  }

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Metadata getMet() {
    return this.met;
  }

  public void setMet(Metadata met) {
    this.met = met;
  }

  public ArrayList<Nd> getNodes() {
    return this.nodes;
  }

  public void setNodes(ArrayList<Nd> nodes) {
    this.nodes = nodes;
  }

  public ArrayList<Tag> getTags() {
    return this.tags;
  }

  public void setTags(ArrayList<Tag> tags) {
    this.tags = tags;
  }

  public Way id(long id) {
    setId(id);
    return this;
  }

  public Way met(Metadata met) {
    setMet(met);
    return this;
  }

  public Way nodes(ArrayList<Nd> nodes) {
    setNodes(nodes);
    return this;
  }

  public Way tags(ArrayList<Tag> tags) {
    setTags(tags);
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
    return Objects.equals(id, way.id) && Objects.equals(met, way.met) && Objects.equals(nodes, way.nodes)
        && Objects.equals(tags, way.tags);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, met, nodes, tags);
  }

  @Override
  public String toString() {
    return "{" + " id='" + getId() + "'" + ", met='" + getMet() + "'" + ", nodes='" + getNodes() + "'" + ", tags='"
        + getTags() + "'" + "}";
  }

}
