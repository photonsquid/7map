package com.sevenmap.data.parsers.osm.Structure.Way;

import java.util.ArrayList;
import java.util.Objects;

import com.sevenmap.data.parsers.osm.Annotations.XMLAttribute;
import com.sevenmap.data.parsers.osm.Annotations.XMLElement;
import com.sevenmap.data.parsers.osm.Structure.HasTag;
import com.sevenmap.data.parsers.osm.Structure.Metadata.Metadata;
import com.sevenmap.data.parsers.osm.Structure.Node.Nd;
import com.sevenmap.data.parsers.osm.Structure.Tag.Tag;

public class Way implements HasTag {

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

  /**
   * find a tag by its key
   * 
   * @param key key of the tag you're searching for
   * @return first Tag in list with key, null if not found
   */
  public Tag findTag(String key) {
    for (Tag tag : this.tags) {
      if (tag.getKey().equals(key)) {
        return tag;
      }
    }
    return null;
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
