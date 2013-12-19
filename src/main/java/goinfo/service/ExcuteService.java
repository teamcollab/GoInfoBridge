package goinfo.service;

import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

/**
 * Created by Spooky on 2013/12/19.
 */
public abstract class ExcuteService {
    protected Environment env;
    protected JdbcTemplate jdbcTemplate;

    abstract public Map excute(Map params);
    public void setEnv(Environment env){
        this.env = env;
    }
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
}
