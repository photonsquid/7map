package com.sevenmap.core;

import org.apache.commons.cli.CommandLine;

public abstract class Loadable {

  protected CommandLine cl;

  /**
   * An object extending from {@code Loadable} must define a load method.
   * 
   * @param cl command line arguments
   */
  public abstract void load(CommandLine cl);

}
