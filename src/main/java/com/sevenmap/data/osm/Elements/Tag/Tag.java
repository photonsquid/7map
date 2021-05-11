package com.sevenmap.data.osm.Elements.Tag;

import java.util.Objects;

import com.sevenmap.data.osm.parser.Annotations.XMLAttribute;
import com.sevenmap.data.osm.parser.Annotations.XMLClass;

@XMLClass(key = "tag")
public class Tag {

  @XMLAttribute
  private String key;
  @XMLAttribute
  private String value;

  public Tag() {
  }

  public Tag(String key, String value) {
    this.key = key;
    this.value = value;
  }

  public String getKey() {
    return this.key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getValue() {
    return this.value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public Tag key(String key) {
    setKey(key);
    return this;
  }

  public Tag value(String value) {
    setValue(value);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Tag)) {
      return false;
    }
    Tag tag = (Tag) o;
    return Objects.equals(key, tag.key) && Objects.equals(value, tag.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, value);
  }

  @Override
  public String toString() {
    return "{" + " key='" + getKey() + "'" + ", value='" + getValue() + "'" + "}";
  }

}
