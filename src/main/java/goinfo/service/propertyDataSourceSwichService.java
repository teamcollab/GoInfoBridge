package goinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

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

        if(name.equals("") || name.equals("major"))
            jdbcTemplate = majorJdbcTemplate;
        else if(name.equals("minor"))
            jdbcTemplate = minorJdbcTemplate;

        return jdbcTemplate;

    }

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(String name){
        NamedParameterJdbcTemplate namedParameterJdbcTemplate=null;
        if(name.equals("") || name.equals("major"))
            namedParameterJdbcTemplate = majorNamedParameterJdbcTemplate;
        else if(name.equals("minor"))
            namedParameterJdbcTemplate = minorNamedParameterJdbcTemplate;

        return namedParameterJdbcTemplate;

    }

}
