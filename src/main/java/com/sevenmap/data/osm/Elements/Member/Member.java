package com.sevenmap.data.osm.Elements.Member;

import java.util.Objects;

import com.sevenmap.data.osm.parser.Annotations.XMLAttribute;

public class Member {
  @XMLAttribute
  private String type;
  @XMLAttribute
  private int ref;
  @XMLAttribute
  private int role;

  public Member() {
  }

  public Member(String type, int ref, int role) {
    this.type = type;
    this.ref = ref;
    this.role = role;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getRef() {
    return this.ref;
  }

  public void setRef(int ref) {
    this.ref = ref;
  }

  public int getRole() {
    return this.role;
  }

  public void setRole(int role) {
    this.role = role;
  }

  public Member type(String type) {
    setType(type);
    return this;
  }

  public Member ref(int ref) {
    setRef(ref);
    return this;
  }

  public Member role(int role) {
    setRole(role);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Member)) {
      return false;
    }
    Member member = (Member) o;
    return Objects.equals(type, member.type) && ref == member.ref && role == member.role;
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, ref, role);
  }

  @Override
  public String toString() {
    return "{" + " type='" + getType() + "'" + ", ref='" + getRef() + "'" + ", role='" + getRole() + "'" + "}";
  }

}
