package goinfo.cfg.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReplyerConfig {


    final static String queueName = "spring-boot.reply";


    @Bean
    public RabbitTemplate replyAmqpTemplate(ConnectionFactory connectionFactory) {

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setReplyQueue(replyQueue());
        rabbitTemplate.setReplyTimeout(60000);
        return rabbitTemplate;

    }

    @Bean
    public SimpleMessageListenerContainer replyListenerContainer(ConnectionFactory connectionFactory, RabbitTemplate replyAmqpTemplate) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueues(replyQueue());
        container.setMessageListener(replyAmqpTemplate);
        return container;
    }

    @Bean
    public Queue replyQueue() {
        return new Queue(queueName);
    }

}

