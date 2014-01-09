package goinfo.cfg.dataSource;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.TomcatDataSourceConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;



@Configuration
@ConfigurationProperties(name = "spring.minor")
public class MinorDatabaseConfig extends TomcatDataSourceConfiguration implements DatabaseConfig {

    @Value("${spring.minor.testOnBorrow}")
    boolean testOnBorrow;
    @Value("${spring.minor.validationQuery}")
    String validationQuery;


    @Bean(name = "minorDataSource")
    public javax.sql.DataSource dataSource() {
        org.apache.tomcat.jdbc.pool.DataSource  poolDataSource = (DataSource) super.dataSource();
        poolDataSource.setTestOnBorrow(testOnBorrow);
        poolDataSource.setValidationQuery(validationQuery);
        return poolDataSource;
    }

    @Bean(name = "minorJdbcTemplate")
    public JdbcTemplate jdbcTemplate(javax.sql.DataSource minorDataSource) {
        return new JdbcTemplate(minorDataSource);
    }

    @Bean(name = "minorNamedParameterJdbcTemplate")
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(javax.sql.DataSource minorDataSource) {
        return new NamedParameterJdbcTemplate(minorDataSource);
    }
}
