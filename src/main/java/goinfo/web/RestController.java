package goinfo.web;

import goinfo.service.QueryApiService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Spooky on 2013/12/19.
 */
@Controller
public class RestController {

    private static Log logger = LogFactory.getLog(RestController.class);

    @Autowired
    private QueryApiService queryApiService;


    @RequestMapping(value ="/rest/query" , method = RequestMethod.POST
            , headers = "Content-Type=application/x-www-form-urlencoded"
            , produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String query(@RequestParam(value="params", required=true)String params) {

        String json = queryApiService.excuteAndGetJson(params);

        return json;
    }
}
