package com.sevenmap.core;

import com.sevenmap.core.Map.Map;
import com.sevenmap.core.UI.UI;

import org.apache.commons.cli.CommandLine;

public class Runtime {

  public void load(CommandLine cl) {

    UI gui = new UI();
    Map map = new Map();

    map.load(cl);
    gui.load(cl);
  }
}
