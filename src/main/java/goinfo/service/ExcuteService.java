package goinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Spooky on 2013/12/23.
 */
@Service
public class ExcuteService {

    @Autowired
    ConvertService convertService;

    @Autowired
    QueryApiService queryService;


    public String excute(String params){
        Map paramsMap = convertService.stringToMap(params);
        Map result =excute(paramsMap);
        return convertService.mapToJsonString(result);
    }


    public Map excute(Map params){
        String action = params.get("action").toString();
        Map result =null;
        if(action.equals("query")){
            result = queryService.excute(params);
        }
        return result;
    }
}
