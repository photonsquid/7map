package com.sevenmap.data_handler;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sevenmap.data_handler.GeoJson.Feature;
import com.sevenmap.data_handler.GeoJson.FeatureCollection;
import com.sevenmap.data_handler.GeoJson.Coordinates.CMultiPolygon;
import com.sevenmap.data_handler.GeoJson.Coordinates.Polygon;
import com.sevenmap.data_handler.GeoJson.Geometry.Geometry;

public class JsonParser {

    private static ObjectMapper mapper = new ObjectMapper();

    public static void parse(String src) {

        try {

            // JSON file to Java object
            FeatureCollection feats = mapper.readValue(new File(src), FeatureCollection.class);
            Feature f = feats.getFeatures().get(0);
            Geometry g = f.getGeometry();
            CMultiPolygon c = (CMultiPolygon) g.getCoordinates();
            ArrayList<Polygon> pG = c.getPolygons();
            System.out.println(c.getType());
            System.out.println(c.getType());


            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public static void main(String[] args) {
        String path = "src/main/resources/maps/custom.geo.json";
        parse(path);

    }
}