package goinfo.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
public class ApiController {

    @Autowired
    RabbitTemplate amqpTemplate;

    @RequestMapping("/test/sendMsg")
    @ResponseBody
    public String sendMsg() {

        Map message = new HashMap<String, Object>();

        message.put("username", "admin");

        message.put("action", "query");  // update, delete, query

        message.put("password", "password");
        message.put("table", "GHSAHMS1");
        message.put("params", new HashMap());
        message.put("queryname", "selectall");

        ObjectMapper mapper = new ObjectMapper();
        String finalJsonString = null;
        try {
            finalJsonString = mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("amqpTemplate.getUUID() = "+ amqpTemplate.getUUID());
        Object o = amqpTemplate.convertSendAndReceive("spring-boot", finalJsonString);
        System.out.println("reply = "+ o);
        return "msg is sended ";
    }
}
