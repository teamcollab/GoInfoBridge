package goinfo.web;

import goinfo.service.ApiFecadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    ApiFecadeService apiFecadeService;



    @RequestMapping(value = "/admin/rungc" , method = RequestMethod.GET)
    @ResponseBody
    public String rungc() {
        Runtime.getRuntime().gc();
        return "Run GC finish";
    }

    @RequestMapping(value = "/admin/info" , method = RequestMethod.GET
            , produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String check() {
        long max = Runtime.getRuntime().maxMemory()/1024/1024;
        long total = Runtime.getRuntime().totalMemory()/1024/1024;
        long free = Runtime.getRuntime().freeMemory()/1024/1024;
        long used = total - free;

        String mem =  String.format("Memory:<br>" +
                "max: %,d mb<br>" +
                "total: %,d mb<br>" +
                "free: %,d mb<br>" +
                "used: %,d mb<br>",
                max, total, free, used);



        String majorConnectMsg = "";


        Map params =new HashMap<String, String>();

        params.put("action", "query");
        params.put("querystring", "select 1");
        params.put("connectname", "major");

        Map result =apiFecadeService.excute(params);

        if(result.get("success").equals(true)){
            majorConnectMsg = "正常";
        }else {
            majorConnectMsg = "失敗<br>訊息："+result.get("errorMessage");
        }



        String minorConnectMsg ="";
        params =new HashMap<String, String>();

        params.put("action", "query");
        params.put("querystring", "select 1");
        params.put("connectname", "minor");

        result =apiFecadeService.excute(params);



        if(result.get("success").equals(true)){
            minorConnectMsg = "正常";
        }else {
            minorConnectMsg = "失敗<br>訊息："+result.get("errorMessage");
        }

        String connect =
                "major connect 連線測試：<br>測試結果："+majorConnectMsg+" <br><br>"
                +"minor  connect 連線測試：<br>測試結果："+minorConnectMsg+" <br><br>";

        return mem +"<br><br>" + connect;
    }
}
