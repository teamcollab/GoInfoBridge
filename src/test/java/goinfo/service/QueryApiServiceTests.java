package goinfo.service;

import goinfo.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles("test")
public class QueryApiServiceTests {
    @Autowired
    QueryService queryService;


    @Test
    public void testSelectone(){

        Map params =new HashMap<String, String>();

        params.put("username", "admin");
        params.put("password", "password");
        params.put("action", "query");
        params.put("queryname", "selectall");
        params.put("connectname", "major");
        Map result = queryService.excute(params);

        System.out.println("test result = "+ result.get("success"));

        assert result.get("success").equals(true);

        List data = (List)result.get("data");

        assert data.size() > 1;


    }
    @Test
    public void testSelectsome(){

        Map values = new HashMap();
        values.put("Code","A001");

        Map params =new HashMap<String, String>();

        params.put("values", values);
        params.put("username", "admin");
        params.put("password", "password");
        params.put("action", "query");
        params.put("queryname", "selectsome");
        params.put("connectname", "major");
        Map result = queryService.excute(params);

        assert result.get("success").equals(true);

        List data = (List)result.get("data");

        assert data.size() == 1;


    }
    @Test
    public void testQueryFail(){

        Map params =new HashMap<String, String>();

        params.put("username", "admin");
        params.put("password", "password");
        params.put("action", "query");
        params.put("queryname", "selecterror");
        params.put("connectname", "major");


        boolean result = true ;

        try{
            queryService.excute(params);
        }catch(Exception e){
            result = false;
            e.printStackTrace();
            System.out.println("exception message = "+e.getLocalizedMessage());
        }

        assert !result;



    }

}
