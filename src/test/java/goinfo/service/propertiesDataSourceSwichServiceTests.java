package goinfo.service;

import goinfo.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles("test")
public class propertiesDataSourceSwichServiceTests {
    @Autowired
    DataSourceSwichService propertyDataSourceSwichService;

    @Test
    public void testGetDatasourceByName(){

        assert propertyDataSourceSwichService.getJdbcTemplete("major") != null;
        assert propertyDataSourceSwichService.getJdbcTemplete("minor") != null;
        assert propertyDataSourceSwichService.getJdbcTemplete("") != null;
        assert propertyDataSourceSwichService.getNamedParameterJdbcTemplate("major") != null;
        assert propertyDataSourceSwichService.getNamedParameterJdbcTemplate("minor") != null;
        assert propertyDataSourceSwichService.getNamedParameterJdbcTemplate("") != null;

    }
    @Test
    public void testGetDatasourceNotfind(){

        String errorMessage="";

        boolean result = true;
        try{
            propertyDataSourceSwichService.getJdbcTemplete("noset");
        }catch(Exception e){
            result = false;
            e.printStackTrace();
            errorMessage = e.getLocalizedMessage();
        }

        assert !result;

        assert errorMessage.equals("查無 noset 連線資訊");

    }

}
