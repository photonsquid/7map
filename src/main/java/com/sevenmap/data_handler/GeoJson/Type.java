package com.sevenmap.data_handler.GeoJson;

import java.util.Objects;

public class Type {
  private String type;

  public Type() {
  }

  public Type(String type) {
    this.type = type;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Type type(String type) {
    setType(type);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Type)) {
            return false;
        }
        Type type = (Type) o;
        return Objects.equals(type, type.type);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(type);
  }

  @Override
  public String toString() {
    return "{" +
      " type='" + getType() + "'" +
      "}";
  }
}
