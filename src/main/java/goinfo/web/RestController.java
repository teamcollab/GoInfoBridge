package goinfo.web;

import goinfo.service.ApiFecadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Spooky on 2013/12/19.
 */
@Controller
public class RestController {
    @Autowired
    private ApiFecadeService apiFecadeService;

    @RequestMapping(value ="/rest/api" , method = {RequestMethod.POST}
            , headers = "Content-Type=text/plain;charset=UTF-8"
            , produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String api(@RequestBody String params) {
        return apiFecadeService.excute(apiFecadeService, params);
    }



}
