package com.sevenmap.data.styles;

import java.util.Objects;

public class Styles {
  private Integer st;

  public Styles() {
  }

  public Styles(Integer st) {
    this.st = st;
  }

  public Integer getSt() {
    return this.st;
  }

  public void setSt(Integer st) {
    this.st = st;
  }

  public Styles st(Integer st) {
    setSt(st);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Styles)) {
            return false;
        }
        Styles styles = (Styles) o;
        return Objects.equals(st, styles.st);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(st);
  }

  @Override
  public String toString() {
    return "{" +
      " st='" + getSt() + "'" +
      "}";
  }

}
