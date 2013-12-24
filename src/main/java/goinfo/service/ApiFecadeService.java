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
    ConvertService convertService;

    @Autowired
    QueryService queryService;


    public String excute(String params){
        Map paramsMap = convertService.stringToMap(params);
        Map result =this.excute(paramsMap);
        return convertService.mapToJsonString(result);
    }


    public Map excute(Map params){

        Assert.notNull(params.get("action"), "action must not be null");

        String action = params.get("action").toString();
        Map result =null;
        if(action.equals("query")){
            result = queryService.excute(params);
        }
        return result;
    }
}
