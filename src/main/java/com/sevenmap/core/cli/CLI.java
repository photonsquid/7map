package com.sevenmap.core.cli;

import java.net.URL;

import com.sevenmap.core.Props;
import com.sevenmap.core.Props.BUILD_TYPE;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class CLI {

  public static Props parseArgs(String[] args) {

    CommandLine commandLine = null;
    Option opBuilder = Option.builder("B").required(false).desc("Force rebuild map from file.").longOpt("build")
        .build();
    Option opMinLon = Option.builder("minLon").required(false)
        .desc("Set minimum longitude bound to the new map to download.").longOpt("maxLongitude").build();
    Option opMaxLon = Option.builder("maxLon").required(false)
        .desc("Set maximum longitude bound to the new map to download.").longOpt("maxLongitude").build();
    Option opMinLat = Option.builder("minLat").required(false)
        .desc("Set minimum latitude bound to the new map to download.").longOpt("maxLongitude").build();
    Option opMaxLat = Option.builder("maxLat").required(false)
        .desc("Set maximum latitude bound to the new map to download.").longOpt("maxLongitude").build();
    Option opFileName = Option.builder("F").required(false).desc("Define the path of the map file to display.")
        .longOpt("fileName").build();
    Option opUrl = Option.builder("U").required(false).desc("Define the URL from wich to download a map.")
        .longOpt("url").build();
    Options options = new Options();
    CommandLineParser parser = new DefaultParser();

    options.addOption(opBuilder);
    options.addOption(opMinLon);
    options.addOption(opMaxLon);
    options.addOption(opMinLat);
    options.addOption(opMaxLat);
    options.addOption(opFileName);
    options.addOption(opUrl);

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
      props.setHasToBuild(BUILD_TYPE.FROM_URL);
    }

    if (commandLine.hasOption("maxLon")) {
      props.setMaxLon(Double.parseDouble(commandLine.getOptionValue("maxLon")));
      props.setHasToBuild(BUILD_TYPE.FROM_URL);
    }

    if (commandLine.hasOption("minLat")) {
      props.setMinLat(Double.parseDouble(commandLine.getOptionValue("minLat")));
      props.setHasToBuild(BUILD_TYPE.FROM_URL);
    }

    if (commandLine.hasOption("maxLat")) {
      props.setMaxLat(Double.parseDouble(commandLine.getOptionValue("minLon")));
      props.setHasToBuild(BUILD_TYPE.FROM_URL);
    }

    if (commandLine.hasOption("F")) {
      props.setMapFile(commandLine.getOptionValue("F"));
      props.setHasToBuild(BUILD_TYPE.FROM_FILE);
    }

    if (commandLine.hasOption("U")) {
      try {

        URL uri = new URL(commandLine.getOptionValue("U"));
        props.setDownloadURL(uri);
        props.setHasToBuild(BUILD_TYPE.FROM_URL);

      } catch (Exception e) {
        // TODO: handle exception
      }

    }
    if (commandLine.hasOption("B")) {
      props.setHasToBuild(BUILD_TYPE.FROM_FILE);
    }

    return props;
  }

}
