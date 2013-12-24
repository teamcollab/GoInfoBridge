package goinfo.service;

import goinfo.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles("test")
public class ApiFecadeServiceTests {

    @Autowired
    ApiFecadeService apiFecadeService;

    @Test
    public void testActionParamsException(){
        Map params =new HashMap<String, String>();

        params.put("username", "admin");
        params.put("password", "password");
        params.put("queryname", "selectall");

        Map result = apiFecadeService.excute(params);

        assert result.get("success").equals(false);
        assert !result.get("errorMessage").equals("");
    }
    @Test
    public void testQuerynameParamsException(){
        Map params =new HashMap<String, String>();

        params.put("username", "admin");
        params.put("password", "password");
        params.put("action", "query");

        Map result = apiFecadeService.excute(params);

        assert result.get("success").equals(false);
        assert !result.get("errorMessage").equals("");
    }
    @Test
    public void testSelectErrorException(){
        Map params =new HashMap<String, String>();

        params.put("username", "admin");
        params.put("password", "password");
        params.put("action", "query");
        params.put("queryname", "selecterror");

        Map result = apiFecadeService.excute(params);

        assert result.get("success").equals(false);
        assert result.get("errorMessage").toString().startsWith("StatementCallback; bad SQL grammar [select * from table]");
    }
    @Test
    public void testUpdateErrorException(){
        Map values = new HashMap();
        values.put("Code","A001");
        values.put("Name","A001_update");
        values.put("Note","update_note");


        Map params =new HashMap<String, String>();

        params.put("username", "admin");
        params.put("password", "password");
        params.put("action", "update");
        params.put("queryname", "updateerror");

        params.put("values", values);

        Map result = apiFecadeService.excute(params);

        assert result.get("success").equals(false);

        assert result.get("errorMessage").toString().startsWith("No value supplied for the SQL parameter 'error'");
    }
}
