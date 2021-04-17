package com.sevenmap.data_handler;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static JsonNode parse(String src) throws IOException{
        return objectMapper.readTree(src);
    }
    
}