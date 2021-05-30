package com.sevenmap.data.parsers.osm.Structure.Member;

import java.util.Objects;

import com.sevenmap.data.parsers.osm.Annotations.XMLAttribute;

public class Member {
  @XMLAttribute
  private String type;
  @XMLAttribute
  private long ref;
  @XMLAttribute
  private long role;

  public Member() {
  }

  public Member(String type, long ref, long role) {
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

  public long getRef() {
    return this.ref;
  }

  public void setRef(long ref) {
    this.ref = ref;
  }

  public long getRole() {
    return this.role;
  }

  public void setRole(long role) {
    this.role = role;
  }

  public Member type(String type) {
    setType(type);
    return this;
  }

  public Member ref(long ref) {
    setRef(ref);
    return this;
  }

  public Member role(long role) {
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
