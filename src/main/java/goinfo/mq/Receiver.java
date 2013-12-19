
package goinfo.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class Receiver {

    RabbitTemplate amqpTemplate;
    public Receiver(RabbitTemplate amqpTemplate){
        this.amqpTemplate = amqpTemplate;

    }

	public String receiveMessage(Object message) {

//        Map<String,Object> map = new HashMap<String,Object>();
//        ObjectMapper mapper = new ObjectMapper();
//
//        try {
//            map = mapper.readValue(message,
//                    new TypeReference<HashMap<String,Object>>(){});
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        System.out.println("Received <" + message + ">");

        return "OK";

    }
}
