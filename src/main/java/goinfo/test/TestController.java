package goinfo.test;

import goinfo.service.ConvertService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
public class TestController {

    @Autowired
    RabbitTemplate amqpTemplate;

    @Autowired
    ConvertService convertService;

    @RequestMapping(value ="/test/sendMsg" , method = {RequestMethod.GET, RequestMethod.OPTIONS}
            , produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String sendMsg() {

        Map message = new HashMap<String, Object>();

        message.put("username", "admin");
        message.put("password", "password");

        message.put("action", "query");  // update, delete, query

        Map values = new HashMap();

        values.put("Code","A00");

        message.put("values", values);
        message.put("queryname", "selectsome");

        String finalJsonString = convertService.mapToJsonString(message);


        System.out.println("send     : "+ finalJsonString);
        Object o = amqpTemplate.convertSendAndReceive("spring-boot", finalJsonString);

        String result = "get reply: "+ o;

        System.out.println(result);
        return result;
    }
}
