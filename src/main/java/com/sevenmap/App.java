package com.sevenmap;

import com.sevenmap.core.CLI;
import com.sevenmap.core.Props;
import com.sevenmap.core.Runtime;

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
