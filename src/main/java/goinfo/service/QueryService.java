package goinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QueryService {

    @Autowired
    @Qualifier("propertyDataSourceSwichService")
    private DataSourceSwichService dataSourceSwichService;
    @Autowired private PropertiesHoldService propertiesHoldService;


    public Map excute(Map params) {


        String queryname = params.get("queryname").toString();
        String connectname = params.get("connectname").toString();
        String querysql = propertiesHoldService.getQueriesProperty(queryname);

        if(querysql == null) Assert.notNull(querysql, queryname + " query not find");

        Map result = new HashMap();
        result.put("success", true);

        Map values = new HashMap();
        if(params.containsKey("values"))
            values = (Map) params.get("values");

        List data = null;

        try {
            if(values.size()>0){
                data = dataSourceSwichService.getNamedParameterJdbcTemplate(connectname).queryForList(querysql, values);
            }else{
                data = dataSourceSwichService.getJdbcTemplete(connectname).queryForList(querysql);
            }
        }catch (DataAccessException e ){
            throw e;
        }

        result.put("data",data);


        return result;
    }


}
