package com.sevenmap;

import com.sevenmap.core.Props;
import com.sevenmap.core.Runtime;
import com.sevenmap.core.cli.CLI;

/**
 * Main application class.
 */
public class App {

  public static void main(String[] args) {

    // Parse args from CLI
    Props props = CLI.parseArgs(args);

    // Launch app
    Runtime rt = new Runtime();
    rt.load(props);
  }

}
