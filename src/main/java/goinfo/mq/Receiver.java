
package goinfo.mq;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import goinfo.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Receiver {

    @Autowired
    private QueryService queryService;

	public String receiveMessage(Object message) {

        HashMap<String, Object> map = new HashMap<String,Object>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            map = mapper.readValue(message.toString(),
                    new TypeReference<HashMap<String,Object>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        String action = map.get("action").toString();

        Map result = queryService.excute(map);


        return result.toString();

    }
}
