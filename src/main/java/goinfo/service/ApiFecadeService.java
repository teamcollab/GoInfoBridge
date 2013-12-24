package goinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * Created by Spooky on 2013/12/23.
 */
@Service
public class ApiFecadeService {


    @Autowired
    QueryService queryService;

    @Autowired
    UpdateService updateService;

    public Map excute(Map params){

        Assert.notNull(params.get("action"), "action must not be null");

        String action = params.get("action").toString();
        Map result =null;
        if(action.equals("query")){
            result = queryService.excute(params);
        }else if(action.equals("update")){
            result = updateService.excute(params);
        }
        return result;
    }
}
