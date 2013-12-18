package goinfo.web;

import goinfo.domain.RepairItem;
import goinfo.service.RepairItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class RepairItemController {

    @Autowired
    private RepairItemRepository repairItemRepository;

    @Value("${selectone}")
    String selectall;

    @RequestMapping("/repairItem/list")
    @ResponseBody
    public Iterable<RepairItem> list() {
        // fetch all customers

        System.out.println("selectall = "+selectall);
        Iterable<RepairItem> repairItems = repairItemRepository.findAll();


        return repairItems;
    }

//    @RequestMapping("/customer/show")
//    @ResponseBody
//    public List<Customer> list(
//            @RequestParam(value="lastName", required=true) String lastName) {
//
//
//        List<Customer> customers = customerRepository.findByLastName(lastName);
//
//        return customers;
//    }

}