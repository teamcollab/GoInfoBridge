package goinfo.mq.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Spooky on 2013/12/17.
 */

@Configuration
public class QuerySender implements CommandLineRunner {

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Waiting five seconds...");
        Thread.sleep(5000);
        System.out.println("Sending message...");

        Map message = new HashMap<String, Object>();

        message.put("username", "admin");

        message.put("action", "query");  // update, delete, query

        message.put("password", "password");
        message.put("table", "GHSAHMS1");
        message.put("params", new HashMap());
        message.put("queryname", "selectall");

        ObjectMapper mapper = new ObjectMapper();
        String finalJsonString = mapper.writeValueAsString(message);

        rabbitTemplate.convertAndSend("spring-boot", finalJsonString);
    }
}
