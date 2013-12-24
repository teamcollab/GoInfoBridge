package goinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QueryService {

    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired private PropertiesHoldService propertiesHoldService;


    public Map excute(Map params) {

        Assert.notNull(params.get("queryname"), "queryname must not be null");
        String queryname = params.get("queryname").toString();
        String querysql = propertiesHoldService.getQueriesProperty(queryname);

        Map result = new HashMap();
        result.put("success", true);

        Map values = new HashMap();
        if(params.containsKey("values"))
            values = (Map) params.get("values");

        List data = null;

        try {
            if(values.size()>0)
                data = namedParameterJdbcTemplate.queryForList(querysql,values);
            else data = jdbcTemplate.queryForList(querysql);
        }catch (DataAccessException e ){
            throw e;
        }

        result.put("data",data);


        return result;
    }


}
