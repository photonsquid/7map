package com.sevenmap.data_handler;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sevenmap.data_handler.GeoJson.Feature;
import com.sevenmap.data_handler.GeoJson.FeatureCollection;
import com.sevenmap.data_handler.GeoJson.Coordinates.CLineString;
import com.sevenmap.data_handler.GeoJson.Coordinates.CPolygon;
import com.sevenmap.data_handler.GeoJson.Geometry.GMultiPolygon;

public class JsonParser {

    private static ObjectMapper mapper = new ObjectMapper();

    public static void parse(String src) {

        try {

            // JSON file to Java object
            FeatureCollection feats = mapper.readValue(new File(src), FeatureCollection.class);

            
            Feature f = feats.getFeatures().get(0);
            GMultiPolygon g = (GMultiPolygon) f.getGeometry();
            CPolygon[] cp =  g.getCoordinates();
            ArrayList<ArrayList<CLineString>> ln = cp[0].getPolygons();

            System.out.println("Coucou");
            System.out.println(ln);
            // CMultiPolygon c = (CMultiPolygon) g.getCoordinates();
            // ArrayList<CPolygon> pG = c.getPolygons();
            // System.out.println(c.getType());
            // System.out.println(c.getType());


            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public static void main(String[] args) {
        String path = "src/main/resources/maps/test.geo.json";
        parse(path);

    }
}