package com.sevenmap.data.parsers.osm.Structure.Node;

import java.util.ArrayList;
import java.util.Objects;

import com.sevenmap.data.parsers.osm.Annotations.XMLAttribute;
import com.sevenmap.data.parsers.osm.Annotations.XMLElement;
import com.sevenmap.data.parsers.osm.Structure.Metadata.Metadata;
import com.sevenmap.data.parsers.osm.Structure.Tag.Tag;

public class Node {

  @XMLAttribute(unique = true)
  private long id;
  @XMLAttribute
  private Double lat;
  @XMLAttribute
  private Double lon;
  @XMLAttribute
  private Metadata met;
  @XMLElement(tag = "tag", valueType = Tag.class)
  private ArrayList<Tag> tags;

  public Node() {
  }

  public Node(long id, Double lat, Double lon, Metadata met, ArrayList<Tag> tags) {
    this.id = id;
    this.lat = lat;
    this.lon = lon;
    this.met = met;
    this.tags = tags;
  }

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Double getLat() {
    return this.lat;
  }

  public void setLat(Double lat) {
    this.lat = lat;
  }

  public Double getLon() {
    return this.lon;
  }

  public void setLon(Double lon) {
    this.lon = lon;
  }

  public Metadata getMet() {
    return this.met;
  }

  public void setMet(Metadata met) {
    this.met = met;
  }

  public ArrayList<Tag> getTags() {
    return this.tags;
  }

  public void setTags(ArrayList<Tag> tags) {
    this.tags = tags;
  }

  public Node id(long id) {
    setId(id);
    return this;
  }

  public Node lat(Double lat) {
    setLat(lat);
    return this;
  }

  public Node lon(Double lon) {
    setLon(lon);
    return this;
  }

  public Node met(Metadata met) {
    setMet(met);
    return this;
  }

  public Node tags(ArrayList<Tag> tags) {
    setTags(tags);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Node)) {
      return false;
    }
    Node node = (Node) o;
    return Objects.equals(id, node.id) && Objects.equals(lat, node.lat) && Objects.equals(lon, node.lon)
        && Objects.equals(met, node.met) && Objects.equals(tags, node.tags);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, lat, lon, met, tags);
  }

  @Override
  public String toString() {
    return "{" + " id='" + getId() + "'" + ", lat='" + getLat() + "'" + ", lon='" + getLon() + "'" + ", met='"
        + getMet() + "'" + ", tags='" + getTags() + "'" + "}";
  }

}
