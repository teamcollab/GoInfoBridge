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
public class UpdateServiceTests {

    @Autowired
    UpdateService updateService;

    @Test
    public void testUpdatenameException(){
        Map values = new HashMap();
        values.put("Code","A001");
        values.put("Name","A001_update");
        values.put("Note","update_note");

        Map params =new HashMap<String, String>();

        params.put("username", "admin");
        params.put("action", "update");
        params.put("password", "password");

        params.put("queryname", "updateerror");
        params.put("values", values);

        boolean result = true;
        String message = "";
        try{
            updateService.excute(params);
        }catch(Exception e){
            result = false;
        }
        assert !result;

    }

    @Test
    public void testUpdate(){

        Map values = new HashMap();
        values.put("Code","A001");
        values.put("Name","A001_update");
        values.put("Note","update_note");

        Map params =new HashMap<String, String>();

        params.put("username", "admin");
        params.put("action", "update");
        params.put("password", "password");

        params.put("queryname", "updateone");
        params.put("connectname", "major");
        params.put("values", values);



        Map result = updateService.excute(params);

        assert result.get("success").equals(true);
        assert result.get("message").equals("受影響的資料共有 1 筆");

    }
    @Test
    public void testUpdateManytype(){
        Map values = new HashMap();
        values.put("Wcode","001");
        values.put("Hcode","A10-05F");
        values.put("Sdate","2012-12-12");
        values.put("ASno",1);

        Map params =new HashMap<String, String>();

        params.put("username", "admin");
        params.put("action", "update");
        params.put("password", "password");

        params.put("queryname", "updatemanytype");
        params.put("connectname", "major");
        params.put("values", values);

        Map result = updateService.excute(params);

        assert result.get("success").equals(true);
        assert result.get("message").equals("受影響的資料共有 1 筆");

    }
}
