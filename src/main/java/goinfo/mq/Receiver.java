
package goinfo.mq;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Receiver {

    RabbitTemplate amqpTemplate;
    public Receiver(RabbitTemplate amqpTemplate){
        this.amqpTemplate = amqpTemplate;

    }

	public String receiveMessage(String message) {

        Map<String,Object> map = new HashMap<String,Object>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            map = mapper.readValue(message,
                    new TypeReference<HashMap<String,Object>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("Received <" + map + ">");
//        amqpTemplate.setCorrelationKey("1111");
//        amqpTemplate.convertSendAndReceive("spring-boot.reply1234","ok!");
        return "OK";

    }
}
