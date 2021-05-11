package com.sevenmap.data.osm.Elements.Node;

import java.util.Objects;

import com.sevenmap.data.osm.Elements.Metadata.Metadata;
import com.sevenmap.data.osm.Elements.Tag.Tag;
import com.sevenmap.data.osm.parser.Annotations.XMLAttribute;
import com.sevenmap.data.osm.parser.Annotations.XMLClass;
import com.sevenmap.data.osm.parser.Annotations.XMLElement;

@XMLClass(key = "node", id = "id")
public class Node {

  @XMLAttribute
  private Integer id;
  @XMLAttribute
  private Double lat;
  @XMLAttribute
  private Double lon;
  @XMLAttribute
  private Metadata met;
  @XMLElement
  private Tag tags;

  public Node(Integer id, Double lat, Double lon) {
    this.id = id;
    this.lat = lat;
    this.lon = lon;
    this.met = new Metadata();
  }

  public Node() {
  }

  public Node(Integer id, Double lat, Double lon, Metadata met, Tag tags) {
    this.id = id;
    this.lat = lat;
    this.lon = lon;
    this.met = met;
    this.tags = tags;
  }

  public Tag getTags() {
    return this.tags;
  }

  public void setTags(Tag tags) {
    this.tags = tags;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
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

  public Node id(Integer id) {
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

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Node)) {
      return false;
    }
    Node node = (Node) o;
    return Objects.equals(id, node.id) && Objects.equals(lat, node.lat) && Objects.equals(lon, node.lon)
        && Objects.equals(met, node.met);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, lat, lon, met);
  }

  @Override
  public String toString() {
    return "{" + " id='" + getId() + "'" + ", lat='" + getLat() + "'" + ", lon='" + getLon() + "'" + ", met='"
        + getMet() + "'" + "}";
  }

}
