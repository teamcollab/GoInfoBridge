package goinfo.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Spooky on 2013/12/18.
 */
public class Replyer {

    @Autowired
    RabbitTemplate replyAmqpTemplate;

    public void replyMessage(String message) {

//        Map<String,Object> map = new HashMap<String,Object>();
//        ObjectMapper mapper = new ObjectMapper();
//
//        try {
//            map = mapper.readValue(message,
//                    new TypeReference<HashMap<String,Object>>(){});
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        replyAmqpTemplate.convertAndSend("spring-boot.reply", message);

    }
}
