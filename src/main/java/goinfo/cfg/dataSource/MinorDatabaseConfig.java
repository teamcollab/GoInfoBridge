package goinfo.cfg.dataSource;

import org.springframework.boot.autoconfigure.jdbc.TomcatDataSourceConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(name = "spring.minor")
public class MinorDatabaseConfig extends TomcatDataSourceConfiguration implements DatabaseConfig {
    @Bean(name = "minorDataSource")
    public DataSource dataSource() {
        return super.dataSource();
    }

    @Bean(name = "minorJdbcTemplate")
    public JdbcTemplate jdbcTemplate(DataSource minorDataSource) {
        return new JdbcTemplate(minorDataSource);
    }

    @Bean(name = "minorNamedParameterJdbcTemplate")
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource minorDataSource) {
        return new NamedParameterJdbcTemplate(minorDataSource);
    }
}
