package goinfo.test;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqClientConfig {


    final static String queueName = "reply";

    @Autowired
    ConnectionFactory connectionFactory;


    @Bean
    public RabbitTemplate amqpTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setReplyQueue(responseQueue());
        rabbitTemplate.setReplyTimeout(5000);
        System.out.println("rabbitTemplate.getUUID() = "+ rabbitTemplate.getUUID());
        return rabbitTemplate;
    }
    @Bean
    public SimpleMessageListenerContainer listenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueues(responseQueue());
        container.setMessageListener(amqpTemplate());

        return container;
    }
    @Bean
    public Queue responseQueue() {
        return new Queue(queueName);
    }

}

