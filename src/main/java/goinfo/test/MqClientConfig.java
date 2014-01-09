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


    @Autowired
    ConnectionFactory connectionFactory;

    final static String queueName = "reply";

    @Bean
    public Queue responseQueue() {
        return new Queue(queueName);
    }

    @Bean
    public RabbitTemplate amqpTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setReplyQueue(responseQueue());
        rabbitTemplate.setReplyTimeout(10000);
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


}

