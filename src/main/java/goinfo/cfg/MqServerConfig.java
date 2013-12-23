package goinfo.cfg;

import goinfo.service.AmqpService;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqServerConfig {


    final static String queueName = "spring-boot";



    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

//    @Bean
//    TopicExchange exchange() {
//        return new TopicExchange("spring-boot-exchange");
//    }
//
//    @Bean
//    Binding binding(Queue queue, TopicExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with(queueName);
//    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    AmqpService receiver() {
        return new AmqpService();
    }

    @Bean
    MessageListenerAdapter listenerAdapter(AmqpService amqpService) {
        return new MessageListenerAdapter(amqpService, "receiveMessage");
    }

}

