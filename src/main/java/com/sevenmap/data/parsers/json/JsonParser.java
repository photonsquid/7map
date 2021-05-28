package com.sevenmap.data.parsers.json;

import java.io.File;

import com.sevenmap.data.parsers.Parser;

public class JsonParser extends Parser {

    public static <T> T parse(String src, Class<T> className) {

        File f = new File(src);
        T tt = (T) createObjectFrom(className);
        return tt;
    }

}
