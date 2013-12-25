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
//        assert propertyDataSourceSwichService.getJdbcTemplete("default") != null;
//        assert propertyDataSourceSwichService.getJdbcTemplete("") != null;

    }
}
