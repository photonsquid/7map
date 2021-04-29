package com.sevenmap.data.osm;

import java.util.Objects;

public class Node implements hasTags {
  private Integer id;
  private Double lat;
  private Double lon;
  private Metadata met;
  private Tags tags;

  public Node(Integer id, Double lat, Double lon) {
    this.id = id;
    this.lat = lat;
    this.lon = lon;
    this.met = new Metadata();
  }

  public Node() {
  }

  public Node(Integer id, Double lat, Double lon, Metadata met, Tags tags) {
    this.id = id;
    this.lat = lat;
    this.lon = lon;
    this.met = met;
    this.tags = tags;
  }

  public Tags getTags() {
    return this.tags;
  }

  public void setTags(Tags tags) {
    this.tags = tags;
  }

  @Override
  public boolean addTag(String key, String value) {
    return this.tags.addTag(key, value);
  }

  @Override
  public String getValueFromTag(String key) {
    return this.tags.getValueByKey(key);
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
