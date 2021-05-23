package com.sevenmap.data.osm.parser;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import com.sevenmap.data.osm.Elements.Root;

import org.jdom.input.SAXBuilder;

public class parser {
  static org.jdom.Document doc;

  public static void main(String[] args) {

    try (BufferedInputStream inputStream = new BufferedInputStream(
        // new
        // URL("https://api.openstreetmap.org/api/0.6/map?bbox=1.45338,43.60116,1.45760,43.60297").openStream());
        new URL("https://api.openstreetmap.org/api/0.6/map?bbox=1.45338,43.60116,1.45760,43.60297").openStream());
        FileOutputStream fileOS = new FileOutputStream("src/main/resources/maps/osm/n7.osm")) {
      byte data[] = new byte[1024];
      int byteContent;
      while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
        fileOS.write(data, 0, byteContent);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Open document
    SAXBuilder sxb = new SAXBuilder();
    try {
      doc = sxb.build(new File("src/main/resources/maps/osm/n7.osm"));
    } catch (Exception e) {
      // TODO: handle error
      System.out.println("erreur.");
    }

    // Parse
    try {
      extracter e = new extracter();
      Root rt = e.extract(doc.getRootElement(), Root.class);
      System.out.println("FIN");

    } catch (Exception e) {
      // TODO: handle errors
      e.printStackTrace();
    }

  }
}