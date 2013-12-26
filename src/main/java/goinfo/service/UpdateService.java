package goinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UpdateService {

    @Autowired
    @Qualifier("propertyDataSourceSwichService")
    private DataSourceSwichService dataSourceSwichService;
    @Autowired private PropertiesHoldService propertiesHoldService;



    public Map excute(Map params) {


        String queryname = params.get("queryname").toString();
        String connectname = params.get("connectname").toString();
        String querysql = propertiesHoldService.getQueriesProperty(queryname);

        Map result = new HashMap();
        result.put("success", true);

        Map values = new HashMap();
        if(params.containsKey("values"))
            values = (Map) params.get("values");

        int sqlExcuteResult = 0;

        try {
            if(values.size()>0)
                sqlExcuteResult = dataSourceSwichService.getNamedParameterJdbcTemplate(connectname).update(querysql, values);
            else sqlExcuteResult = dataSourceSwichService.getJdbcTemplete(connectname).update(querysql);
        }catch (DataAccessException e ){
            throw e;
        }

        result.put("message","受影響的資料共有 "+sqlExcuteResult+" 筆");

    return result;
}
}
