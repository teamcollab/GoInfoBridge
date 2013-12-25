package goinfo.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public interface DataSourceSwichService {

    public JdbcTemplate getJdbcTemplete(String name);

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(String name);
}
