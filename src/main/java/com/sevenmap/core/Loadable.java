package com.sevenmap.core;

public abstract class Loadable {

  protected Props props;

  public Loadable(Props props) {
    this.props = props;
  }

  /**
   * An object extending from {@code Loadable} must define a load method.
   * 
   * @param cl command line arguments
   */
  public abstract void load();

}
