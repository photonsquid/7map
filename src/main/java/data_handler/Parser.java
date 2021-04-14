package data_handler;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {

    private static ObjetMapper objectMapper = new ObjectMapper();

    public static JsonNode parse(String src){
        return objectMapper.readTree(src);
    }
    
}