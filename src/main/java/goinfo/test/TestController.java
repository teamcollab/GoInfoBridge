package goinfo.test;

import goinfo.service.ParserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
public class TestController {

    @Autowired
    RabbitTemplate amqpTemplate;

    @Autowired
    ParserService parserService;

    @RequestMapping("/test/sendMsg")
    @ResponseBody
    public String sendMsg() {

        Map message = new HashMap<String, Object>();

        message.put("username", "admin");
        message.put("password", "password");

        message.put("action", "query");  // update, delete, query
        message.put("values", new HashMap());
        message.put("queryname", "selectall");

        String finalJsonString = parserService.mapToJsonString(message);


        System.out.println("send     : "+ finalJsonString);
        Object o = amqpTemplate.convertSendAndReceive("spring-boot", finalJsonString);

        String result = "get reply: "+ o;

        System.out.println(result);
        return result;
    }
}
