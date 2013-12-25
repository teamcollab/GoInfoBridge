package goinfo.cfg;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

public interface DatabaseConfig {
    public DataSource dataSource();
    public JdbcTemplate jdbcTemplate(DataSource majorDataSource);
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource majorDataSource);
}
