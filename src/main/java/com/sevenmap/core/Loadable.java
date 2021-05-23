package com.sevenmap.core;

import org.apache.commons.cli.CommandLine;

public abstract class Loadable {

  protected CommandLine cl;

  public abstract void load(CommandLine cl);

}
