package goinfo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ConvertService {

    public Map stringToMap(String message){
        HashMap<String, Object> map = new HashMap<String,Object>();
        ObjectMapper mapper = new ObjectMapper();


        try {
            map = mapper.readValue(message,
                    new TypeReference<HashMap<String,Object>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }


        return map;
    }

    public String mapToJsonString(Map map){
        ObjectMapper mapper = new ObjectMapper();
        String result = "{}";
        try {
            result = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return result;
    }

}
