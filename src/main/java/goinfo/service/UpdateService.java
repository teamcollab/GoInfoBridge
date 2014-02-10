package goinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        if(params.containsKey("values")){
            values = (Map) params.get("values");

            for (Object currentKey : values.keySet())if(values.get(currentKey) != null){

                Object entry = values.get(currentKey);

                System.out.println(entry.toString());
                Date d = null;
                try {
                    d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss zzz").parse(entry.toString());
                } catch (ParseException e) {
                }
                if (d == null) try {
                    d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(entry.toString());
                } catch (ParseException e) {
                }

                if (d == null) try {
                    d = new SimpleDateFormat("yyyy-MM-dd").parse(entry.toString());
                } catch (ParseException e) {
                }

                if (d != null){
                    entry = new java.sql.Date(d.getTime());
                    values.put(currentKey, entry);
                }

            }

        }
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
