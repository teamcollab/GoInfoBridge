
package goinfo.mq;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Receiver {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    Replyer replyer;


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
        replyer.replyMessage("result is ok!!");

    }
}
