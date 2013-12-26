package goinfo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
@ActiveProfiles("test")
public class ApiFecadeServiceTests {

    @Autowired
    ApiFecadeService apiFecadeService;

    @Test
    public void testNoConnectNameUseMajorConnectionRunOk(){
        Map params =new HashMap<String, String>();

        params.put("username", "admin");
        params.put("password", "password");
        params.put("queryname", "selectall");
        params.put("action", "query");

        Map result = apiFecadeService.excute(params);

        assert result.get("success").equals(true);
        assert result.get("data") != null;
    }

    @Test
    public void testNoSetActionException(){
        Map params =new HashMap<String, String>();

        params.put("username", "admin");
        params.put("password", "password");
        params.put("queryname", "selectall");

        Map result = apiFecadeService.excute(params);

        assert result.get("success").equals(false);
        assert !result.get("errorMessage").equals("");
    }
    @Test
    public void testNoSetQuerynameException(){
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
        params.put("connectname", "major");
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
        params.put("connectname", "major");
        params.put("values", values);

        Map result = apiFecadeService.excute(params);

        assert result.get("success").equals(false);

        assert result.get("errorMessage").toString().startsWith("No value supplied for the SQL parameter 'error'");
    }

    @Test
    public void testActionNameNotSupportException(){

        Map params =new HashMap<String, String>();
        params.put("username", "admin");
        params.put("password", "password");
        params.put("action", "testaction");
        params.put("queryname", "updateone");
        params.put("connectname", "major");

        Map result = apiFecadeService.excute(params);

        assert result.get("success").equals(false);

        assert result.get("errorMessage").toString().startsWith("testaction not support");
    }
}
