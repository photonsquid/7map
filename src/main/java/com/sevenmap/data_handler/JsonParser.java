package com.sevenmap.data_handler;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sevenmap.data_handler.GeoJson.FeatureCollection;

public class JsonParser {

    private static ObjectMapper mapper = new ObjectMapper();

    public static void parse(String src) {

        try {

            // JSON file to Java object
            FeatureCollection staff = mapper.readValue(new File(src), FeatureCollection.class);

            // compact print
            // System.out.println(staff);

            // pretty print
            String prettyStaff1 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(staff);

            System.out.println(prettyStaff1);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public static void main(String[] args) {
        String path = "src/main/resources/maps/custom.geo.json";
        parse(path);

    }
}