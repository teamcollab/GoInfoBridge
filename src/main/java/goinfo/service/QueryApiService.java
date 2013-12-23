package goinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QueryApiService {

    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public Map excute(Map params) {
        Map result = new HashMap();

        result.put("success", true);

        String queryname = params.get("queryname").toString();

        String querysql = SingletonsService.getQueriesProperties().getProperty(queryname);

        Map values = new HashMap();
        if(params.containsKey("values"))
            values = (Map) params.get("values");

        List data = null;


        try {
            if(values.size()>0)
                data = namedParameterJdbcTemplate.queryForList(querysql, (Map<String, ?>) params.get("values"));
            else data = jdbcTemplate.queryForList(querysql);

        }catch (DataAccessException e ){

            throw e;

        }

        result.put("data",data);


        return result;
    }


}
