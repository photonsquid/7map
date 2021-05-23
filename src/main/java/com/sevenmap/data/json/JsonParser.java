package com.sevenmap.data.json;

import java.io.File;

public class JsonParser {

    public static void parse(String src) {

        File f = new File(src);
    }

    public static void main(String[] args) {
        String path = "src/main/resources/maps/test.geo.json";
        parse(path);

    }
}
