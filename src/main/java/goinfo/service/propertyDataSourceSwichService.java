package goinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * TODO: 之後換成讀取資料庫裡的連線資訊，並且在啟動伺服器時建立 dataSource
 */
@Service
public class propertyDataSourceSwichService implements DataSourceSwichService {

    @Autowired
    JdbcTemplate majorJdbcTemplate;
    @Autowired
    NamedParameterJdbcTemplate majorNamedParameterJdbcTemplate;

    @Autowired
    JdbcTemplate minorJdbcTemplate;
    @Autowired
    NamedParameterJdbcTemplate minorNamedParameterJdbcTemplate;

    public JdbcTemplate getJdbcTemplete(String name){
        JdbcTemplate jdbcTemplate=null;

        if(name.equals("major"))
            jdbcTemplate = majorJdbcTemplate;
        else if(name.equals("minor"))
            jdbcTemplate = minorJdbcTemplate;

        Assert.notNull(jdbcTemplate, "查無 "+name+" 連線資訊");

        return jdbcTemplate;

    }

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(String name){
        if(name.equals("major"))
            return majorNamedParameterJdbcTemplate;
        else if(name.equals("minor"))
            return minorNamedParameterJdbcTemplate;
        else return null;

    }

}
