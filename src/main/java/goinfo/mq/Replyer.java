package goinfo.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class Replyer {

    RabbitTemplate rabbitTemplate;

    public Replyer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

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

        rabbitTemplate.convertAndSend("spring-boot.reply", message);

    }
}
