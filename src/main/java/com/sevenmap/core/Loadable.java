package com.sevenmap.core;

public abstract class Loadable {

  protected Props props;

  public Loadable(Props props) {
    this.props = props;
  }

  /**
   * An object extending from {@code Loadable} must define a load method. <br>
   * But what is the point if you don't use it ?
   */
  public abstract void load();

}
