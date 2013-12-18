package goinfo.mq.test;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Spooky on 2013/12/18.
 */
public class Client {
    @Autowired
    RabbitTemplate rabbitTemplate;


    public void receiveMessage(String message) {

        System.out.println("get response <" + message + ">");


    }
}
