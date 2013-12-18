package goinfo.web;

import goinfo.mq.test.Sender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Spooky on 2013/12/18.
 */
@Controller
public class ApiController {

    Sender sender;

    @RequestMapping("/test/sendmsg")
    @ResponseBody
    public String sendmsg() {

        Sender sender =new Sender();
        try {
            sender.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "message sended";
    }
}
