package com.sevenmap.data.osm.parser;

import java.io.File;

import com.sevenmap.data.osm.Elements.Root;

import org.jdom.input.SAXBuilder;

public class parser {
  static org.jdom.Document doc;

  public static void main(String[] args) {

    // Open document
    SAXBuilder sxb = new SAXBuilder();
    try {
      doc = sxb.build(new File("src/main/resources/maps/osm/test.osm.xml"));
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