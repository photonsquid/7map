package com.sevenmap.core.cli;

import com.sevenmap.core.Props;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class CLI {

  public static Props parseArgs(String[] args) {

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
    Option opFileName = Option.builder("F").required(false).desc("Define the path of the map file to display.")
        .longOpt("fileName").build();
    Options options = new Options();
    CommandLineParser parser = new DefaultParser();

    options.addOption(opBuilder);
    options.addOption(opMinLon);
    options.addOption(opMaxLon);
    options.addOption(opMinLat);
    options.addOption(opMaxLat);
    options.addOption(opFileName);

    try {
      commandLine = parser.parse(options, args);

      {
        String[] remainder = commandLine.getArgs();
        if (remainder.length > 0) {
          // TODO: replace by @kingussopp logger
          System.out.print("Unhandled arguments: ");
          for (String argument : remainder) {
            System.out.print(argument);
            System.out.print(" ");
          }

          System.out.println();
        }
      }

    } catch (org.apache.commons.cli.ParseException exception) {
      System.out.print("Parse error: ");
      System.out.println(exception.getMessage());
    }

    Props props = new Props();

    if (commandLine.hasOption("minLon")) {
      props.setMinLon(Double.parseDouble(commandLine.getOptionValue("minLon")));
    }

    if (commandLine.hasOption("maxLon")) {
      props.setMaxLon(Double.parseDouble(commandLine.getOptionValue("maxLon")));
    }

    if (commandLine.hasOption("minLat")) {
      props.setMinLat(Double.parseDouble(commandLine.getOptionValue("minLat")));
    }

    if (commandLine.hasOption("maxLat")) {
      props.setMaxLat(Double.parseDouble(commandLine.getOptionValue("minLon")));
    }

    if (commandLine.hasOption("F")) {
      props.setMapFile(commandLine.getOptionValue("F"));
    }

    if (commandLine.hasOption("B")) {
      props.setHasToBuild(true);
    } else {
      props.setHasToBuild(false);
    }

    return props;
  }

}
