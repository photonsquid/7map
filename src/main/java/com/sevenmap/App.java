package com.sevenmap;

import com.sevenmap.core.Runtime;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * Main application class.
 */
public class App {

  // ========================== default values ==========================

  public static void main(String[] args) {

    CommandLine cl = parseArgs(args);

    Runtime rt = new Runtime();
    rt.load(cl);

    // ======================== map displayer =========================
    // This is done whenever the user want to display a map.
    // ================================================================

  }

  private static CommandLine parseArgs(String[] args) {

    CommandLine commandLine = null;
    Option opBuilder = Option.builder("B").required(false).desc("Download and build the maps before display it")
        .longOpt("build").build();

    Option opMinLon = Option.builder("minLon").required(false)
        .desc("Set minimum longitude bound to the new map to download. Only if there is -B option.")
        .longOpt("maxLongitude").build();
    Option opMaxLon = Option.builder("maxLon").required(false)
        .desc("Set maximum longitude bound to the new map to download. Only if there is -B option.")
        .longOpt("maxLongitude").build();
    Option opMinLat = Option.builder("minLat").required(false)
        .desc("Set minimum latitude bound to the new map to download. Only if there is -B option.")
        .longOpt("maxLongitude").build();

    Option opMaxLat = Option.builder("maxLat").required(false)
        .desc("Set maximum latitude bound to the new map to download. Only if there is -B option.")
        .longOpt("maxLongitude").build();
    Options options = new Options();
    CommandLineParser parser = new DefaultParser();

    options.addOption(opBuilder);
    options.addOption(opMinLon);
    options.addOption(opMaxLon);
    options.addOption(opMinLat);
    options.addOption(opMaxLat);

    try {
      commandLine = parser.parse(options, args);

      {
        String[] remainder = commandLine.getArgs();
        // TODO: replace by @kingussopp logger
        System.out.print("Unhandled arguments: ");
        for (String argument : remainder) {
          System.out.print(argument);
          System.out.print(" ");
        }

        System.out.println();
      }

    } catch (org.apache.commons.cli.ParseException exception) {
      System.out.print("Parse error: ");
      System.out.println(exception.getMessage());
    }
    return commandLine;
  }

}
