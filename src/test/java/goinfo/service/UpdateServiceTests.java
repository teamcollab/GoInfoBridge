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
        Map params =new HashMap<String, String>();

        params.put("username", "admin");
        params.put("action", "update");

        boolean result =true;
        String message= null;

        try{
            updateService.excute(params);
        }catch(Exception e){
            result = false;
            message = e.getMessage();
        }
        assert !result;
        assert !message.equals("");

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

        params.put("updatename", "updateone");
        params.put("values", values);



        Map result = updateService.excute(params);

        assert result.get("success").equals(true);

    }

}
