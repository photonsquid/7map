package com.sevenmap.data.osm;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import com.sevenmap.data.osm.helpers.extracter;

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

    Nodes nodes = new Nodes();
    // Boucler sur tous les noeuds
    Iterator<?> i = noeuds.iterator();
    while (i.hasNext()) {
      Element courant = (Element) i.next();
      Node nn = extracter.extractData(i, Node.class);
      // Extract data
      Integer id = Integer.parseInt(courant.getAttributeValue("id"));
      Double lat = Double.parseDouble(courant.getAttributeValue("lat"));
      Double lon = Double.parseDouble(courant.getAttributeValue("lon"));
      String user = courant.getAttributeValue("user");
      Integer uid = Integer.parseInt(courant.getAttributeValue("uid"));
      Boolean visible = Boolean.parseBoolean(courant.getAttributeValue("visible"));
      Integer version = Integer.parseInt(courant.getAttributeValue("version"));
      Integer changeset = Integer.parseInt(courant.getAttributeValue("changeset"));
      String timestamp = courant.getAttributeValue("timestamp");
      Tags tags = new Tags();

      List<?> a = courant.getContent(new ElementFilter("tag"));
      Iterator<?> j = a.iterator();

      while (j.hasNext()) {
        Element courantJ = (Element) j.next();
        String key = courantJ.getAttributeValue("k");
        String value = courantJ.getAttributeValue("v");
        tags.addTag(key, value);
      }

      // addNode
      nodes.addNode(new Node(id, lat, lon, new Metadata(user, uid, visible, version, changeset, timestamp), tags));

    }

    // Pour chaque noeud, on crée un objet geojson que l'on envoie directement à la
    // base de donnée

    System.out.println("FIN");
  }
}