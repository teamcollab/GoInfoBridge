package goinfo.cfg;

import goinfo.web.AmqpController;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqServerConfig {



//    @Bean
//    TopicExchange exchange() {
//        return new TopicExchange("spring-boot-exchange");
//    }
//
//    @Bean
//    Binding binding(Queue queue, TopicExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with(queueName);
//    }

    @Autowired
    ConnectionFactory connectionFactory;

    final static String queueName = "spring-boot";

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    AmqpController receiver() {
        return new AmqpController();
    }

    @Bean
    MessageListenerAdapter listenerAdapter(AmqpController receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    SimpleMessageListenerContainer container(MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueues(queue());
        container.setMessageListener(listenerAdapter);
        return container;
    }



}

