package goinfo.service;

import goinfo.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class ExcuteApiServiceTests {

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
        assert !result.get("errorMessage").equals("");
    }

}
