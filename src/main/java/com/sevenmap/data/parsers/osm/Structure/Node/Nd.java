package com.sevenmap.data.parsers.osm.Structure.Node;

import java.util.Objects;

import com.sevenmap.data.parsers.osm.Annotations.XMLAttribute;

public class Nd {

  @XMLAttribute(unique = true)
  private Long ref;

  public Nd() {
  }

  public Nd(Long ref) {
    this.ref = ref;
  }

  public Long getRef() {
    return this.ref;
  }

  public void setRef(Long ref) {
    this.ref = ref;
  }

  public Nd ref(Long ref) {
    setRef(ref);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Nd)) {
      return false;
    }
    Nd nd = (Nd) o;
    return Objects.equals(ref, nd.ref);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(ref);
  }

  @Override
  public String toString() {
    return "{" + " ref='" + getRef() + "'" + "}";
  }

}
