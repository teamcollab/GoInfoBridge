package goinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QueryService{

    @Autowired
    private Environment env;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Map excute(Map params) {


        Map result = new HashMap();

        result.put("success", true);

        String queryname = params.get("queryname").toString();
        String querysql = env.getProperty(queryname);

        List data = jdbcTemplate.queryForList(querysql);

        result.put("data",data);


        return result;
    }
}
