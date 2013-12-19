package goinfo.web;

import goinfo.domain.RepairItem;
import goinfo.service.RepairItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class RepairItemController {

    @Autowired
    private RepairItemRepository repairItemRepository;


    @RequestMapping("/repairItem/list")
    @ResponseBody
    public Iterable<RepairItem> list() {
        // fetch all customers
        Iterable<RepairItem> repairItems = repairItemRepository.findAll();
        return repairItems;
    }

}