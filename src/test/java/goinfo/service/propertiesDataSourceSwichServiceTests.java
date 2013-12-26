package goinfo.service;

import goinfo.Application;
import goinfo.TestDataBaseCreater;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles("test")
public class PropertiesDataSourceSwichServiceTests {
    @Autowired
    DataSourceSwichService propertyDataSourceSwichService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeClass
    public static void createDatabase(){
        TestDataBaseCreater.createDatabase();
    }

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
    public void testDataSourceInfoCorrect() throws SQLException {

        JdbcTemplate majorJdbcTemplate= propertyDataSourceSwichService.getJdbcTemplete("major");
        JdbcTemplate minorJdbcTemplate= propertyDataSourceSwichService.getJdbcTemplete("minor");

        DatabaseMetaData major =  majorJdbcTemplate.getDataSource().getConnection().getMetaData();
        DatabaseMetaData minor =  minorJdbcTemplate.getDataSource().getConnection().getMetaData();
        DatabaseMetaData def =  jdbcTemplate.getDataSource().getConnection().getMetaData();


        assert major.getURL().equals("jdbc:hsqldb:mem:majorDb");
        assert minor.getURL().equals("jdbc:hsqldb:mem:minorDb");
        assert def.getURL().equals("jdbc:hsqldb:mem:defaultDb");

        List majorData = majorJdbcTemplate.queryForList("select * from TEST_TABLE");
        List minorData = minorJdbcTemplate.queryForList("select * from TEST_TABLE");

        assert !majorData.equals(minorData);
        assert !majorData.get(0).toString().equals(minorData.get(0).toString());




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
