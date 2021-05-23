package com.sevenmap;

import java.io.File;

import com.sevenmap.data.optiObj.LightObj;
import com.sevenmap.data.osm.Elements.Bounds.Bounds;
import com.sevenmap.data.osm.api.OSMAPI;

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
  private static String defaultMapFileName = "src/main/resources/maps/osm/n7.osm";
  // Default bounds (arround ENSEEIHT school)
  private static Double defaultMinLon = 1.45338;
  private static Double defaultMaxLon = 1.45760;
  private static Double defaultMinLat = 43.60116;
  private static Double defaultMaxLat = 43.60297;

  public static void main(String[] args) {

    CommandLine cl = parseArgs(args);

    if (cl.hasOption("B")) {
      // ========================== map loader ==========================
      // This is supposed to be done once, when the user load a new map.
      // ================================================================

      // Create OSM Map
      String fileName = cl.getOptionValue("F", defaultMapFileName);
      Bounds N7Bounds = parseBounds(cl);
      File n7Map = new File(fileName);
      OSMAPI OSMMap = new OSMAPI(N7Bounds, n7Map);

      // Download new map
      OSMMap.downloadMap();

      // Parse data
      OSMMap.parse();

      // Convert into optimized files
      LightObj ltObj = new LightObj(OSMMap);
      ltObj.convertObj();
    }

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

  private static Bounds parseBounds(CommandLine cl) {

    // Set default bounds (arround ENSEEIHT school)
    Double minLon = defaultMinLon;
    Double maxLon = defaultMaxLon;
    Double minLat = defaultMinLat;
    Double maxLat = defaultMaxLat;

    // If there is all four options, read them from args
    if (cl.hasOption("minLon") & cl.hasOption("maxLon") & cl.hasOption("minLat") & cl.hasOption("maxLat")) {
      minLon = Double.parseDouble(cl.getOptionValue("minLon", minLon.toString()));
      maxLon = Double.parseDouble(cl.getOptionValue("maxLon", maxLon.toString()));
      minLat = Double.parseDouble(cl.getOptionValue("minLat", minLat.toString()));
      maxLat = Double.parseDouble(cl.getOptionValue("maxLat", maxLat.toString()));
    }

    // Return them
    return new Bounds(minLon, maxLon, minLat, maxLat);
  }

}
