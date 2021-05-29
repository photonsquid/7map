package com.sevenmap.data.parsers.json;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sevenmap.data.parsers.Parser;

public class JsonParser extends Parser {

    public static <T> T parse(String src, Class<T> className) {
        ObjectMapper mapper = new ObjectMapper();

        T tt = (T) createObjectFrom(className);
        try {

            // JSON file to Java object
            tt = mapper.readValue(new File(src), className);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return tt;
    }

}
