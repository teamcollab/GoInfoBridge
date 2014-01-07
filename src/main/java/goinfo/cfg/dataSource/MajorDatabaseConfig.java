package goinfo.cfg.dataSource;

import org.springframework.boot.autoconfigure.jdbc.TomcatDataSourceConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(name = "spring.major")
public class MajorDatabaseConfig extends TomcatDataSourceConfiguration implements DatabaseConfig {
    @Bean(name = "majorDataSource")
    public DataSource dataSource() {
        return super.dataSource();
    }

    @Bean(name = "majorJdbcTemplate")
    public JdbcTemplate jdbcTemplate(DataSource majorDataSource) {
        return new JdbcTemplate(majorDataSource);
}

    @Bean(name = "majorNamedParameterJdbcTemplate")
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource majorDataSource) {
        return new NamedParameterJdbcTemplate(majorDataSource);
    }
}
