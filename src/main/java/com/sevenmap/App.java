package com.sevenmap;

import com.sevenmap.core.Props;
import com.sevenmap.core.Runtime;
import com.sevenmap.core.cli.CLI;

/**
 * Main application class.
 */
public class App {

  public static void main(String[] args) {
    // Create new props
    Props props = new Props();

    // Parse args from CLI
    CLI.parseArgs(args, props);

    // Launch app
    Runtime rt = new Runtime();
    rt.load(props);
  }

}
