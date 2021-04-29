package com.sevenmap.data.osm;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.jdom.Element;
import org.jdom.filter.ElementFilter;
import org.jdom.filter.Filter;
import org.jdom.input.SAXBuilder;

public class osm {
  static org.jdom.Document document;

  static Filter boundsFilter = new Filter() {
    public boolean matches(Object ob) {
      if (!(ob instanceof Element)) {
        return false;
      }
      Element element = (Element) ob;
      Element prenoms = element.getChild("node");
      if (prenoms == null) {
        return false;
      } else {
        return true;
      }
    }
  };

  public static void main(String[] args) {

    SAXBuilder sxb = new SAXBuilder();
    try {
      document = sxb.build(new File("src/main/resources/maps/osm/test.osm.xml"));
    } catch (Exception e) {
      System.out.println("erreur.");
    }

    Element racine = document.getRootElement();

    System.out.println("Version de l'API OSM : " + racine.getAttributeValue("version"));
    List<?> noeuds = racine.getContent(new ElementFilter("node"));

    Iterator<?> i = noeuds.iterator();
    while (i.hasNext()) {
      Element courant = (Element) i.next();
      String id = courant.getAttributeValue("id");
      if (id.equals("1831881213")) {
        List<?> a = courant.getChildren("tag");
        Iterator<?> j = a.iterator();

        while (j.hasNext()) {
          Element courantJ = (Element) j.next();
          System.out.println(courantJ.getAttributeValue("k"));
        }
      }
    }

    // Pour chaque noeud, on crée un objet geojson que l'on envoie directement à la
    // base de donnée

    System.out.println("FIN");
  }
}