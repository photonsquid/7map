package com.sevenmap.data.osm.Elements.Relation;

import java.util.ArrayList;
import java.util.Objects;

import com.sevenmap.data.osm.Elements.Member.Member;
import com.sevenmap.data.osm.Elements.Metadata.Metadata;
import com.sevenmap.data.osm.Elements.Tag.Tag;
import com.sevenmap.data.osm.parser.Annotations.XMLAttribute;
import com.sevenmap.data.osm.parser.Annotations.XMLElement;

public class Relation {
  @XMLAttribute(unique = true)
  private long id;
  @XMLAttribute
  private Metadata met;
  @XMLElement(tag = "member", valueType = Member.class)
  private ArrayList<Member> members;
  @XMLElement(tag = "tag", valueType = Tag.class)
  private ArrayList<Tag> tags;

  public Relation() {
  }

  public Relation(long id, Metadata met, ArrayList<Member> members, ArrayList<Tag> tags) {
    this.id = id;
    this.met = met;
    this.members = members;
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

  public ArrayList<Member> getMembers() {
    return this.members;
  }

  public void setMembers(ArrayList<Member> members) {
    this.members = members;
  }

  public ArrayList<Tag> getTags() {
    return this.tags;
  }

  public void setTags(ArrayList<Tag> tags) {
    this.tags = tags;
  }

  public Relation id(long id) {
    setId(id);
    return this;
  }

  public Relation met(Metadata met) {
    setMet(met);
    return this;
  }

  public Relation members(ArrayList<Member> members) {
    setMembers(members);
    return this;
  }

  public Relation tags(ArrayList<Tag> tags) {
    setTags(tags);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Relation)) {
      return false;
    }
    Relation relation = (Relation) o;
    return Objects.equals(id, relation.id) && Objects.equals(met, relation.met)
        && Objects.equals(members, relation.members) && Objects.equals(tags, relation.tags);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, met, members, tags);
  }

  @Override
  public String toString() {
    return "{" + " id='" + getId() + "'" + ", met='" + getMet() + "'" + ", members='" + getMembers() + "'" + ", tags='"
        + getTags() + "'" + "}";
  }

}
