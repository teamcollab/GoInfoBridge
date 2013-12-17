package goinfo.mq.test;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Spooky on 2013/12/17.
 */

@Configuration
public class QuerySender implements CommandLineRunner {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Waiting five seconds...");
        Thread.sleep(5000);
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend("spring-boot", "Hello from RabbitMQ!");
    }
}
