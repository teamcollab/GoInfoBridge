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
    private QueryService queryService;

    @Autowired
    private UpdateService updateService;

    @Autowired
    private ConvertService convertService;

    public String excute(ApiFecadeService _this,String params){
        // pass _this because _this has process AOP

        Map paramsMap = convertService.stringToMap(params);
        Map result =_this.excute(paramsMap);
        String jsonString =convertService.mapToJsonString(result);
        return jsonString;
    }


    public Map excute(Map params){

        Assert.notNull(params.get("action"), "action must not be null");
        Assert.notNull(params.get("queryname"), "queryname must not be null");

        if(!params.containsKey("connectname"))params.put("connectname", "");

        String action = params.get("action").toString();
        Map result =null;
        if(action.equals("query")){
            result = queryService.excute(params);
        }else if(action.equals("update") || action.equals("delete") || action.equals("create")){
            result = updateService.excute(params);
        }
        return result;
    }
}
