package goinfo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {

    @RequestMapping(value = "/admin/mem", method = {RequestMethod.GET}
            , produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String mem() {
        long max = Runtime.getRuntime().maxMemory();
        long total = Runtime.getRuntime().totalMemory();
        long free = Runtime.getRuntime().freeMemory();
        long used = total - free;

        return String.format("Memory:\n" +
                "max: %,d bytes\n" +
                "total: %,d bytes\n" +
                "free: %,d bytes\n" +
                "used: %,d bytes\n",
                max, total, free, used);
    }

    @RequestMapping(value = "/admin/rungc" , method = RequestMethod.GET)
    @ResponseBody
    public String rungc() {
        Runtime.getRuntime().gc();
        return "Run GC finish";
    }
}
