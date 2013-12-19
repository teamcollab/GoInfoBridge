package goinfo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryService extends ExcuteService{



    @Override
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
