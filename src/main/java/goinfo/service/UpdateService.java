package goinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UpdateService {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired private JdbcTemplate jdbcTemplate;



    public Map excute(Map params) {


        String queryname = params.get("queryname").toString();
        String querysql = PropertiesHoldService.getQueriesProperties().getProperty(queryname);

        Map result = new HashMap();
        result.put("success", true);

        Map values = new HashMap();
        if(params.containsKey("values"))
            values = (Map) params.get("values");

        int sqlExcuteResult = 0;

        System.out.println("values = "+values);

        try {
            if(values.size()>0)
                sqlExcuteResult = namedParameterJdbcTemplate.update(querysql, values);
            else sqlExcuteResult = jdbcTemplate.update(querysql);
        }catch (DataAccessException e ){
            throw e;
        }

        result.put("message","update "+sqlExcuteResult+" rows data");

    return result;
}
}
