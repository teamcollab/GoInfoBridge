
package goinfo.mq;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Receiver {

	public void receiveMessage(String message) {

        Map<String,Object> map = new HashMap<String,Object>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            map = mapper.readValue(message,
                    new TypeReference<HashMap<String,Object>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("Received <" + map + ">");

    }
}
