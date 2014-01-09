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
@ConfigurationProperties(name = "spring.major")
public class MajorDatabaseConfig extends TomcatDataSourceConfiguration implements DatabaseConfig {

    @Value("${spring.major.testOnBorrow}")
    boolean testOnBorrow;
    @Value("${spring.major.validationQuery}")
    String validationQuery;

    @Bean(name = "majorDataSource")
    public javax.sql.DataSource dataSource() {
        org.apache.tomcat.jdbc.pool.DataSource  poolDataSource = (DataSource) super.dataSource();
        poolDataSource.setTestOnBorrow(testOnBorrow);
        poolDataSource.setValidationQuery(validationQuery);
        return poolDataSource;
    }

    @Bean(name = "majorJdbcTemplate")
    public JdbcTemplate jdbcTemplate(javax.sql.DataSource majorDataSource) {
        return new JdbcTemplate(majorDataSource);
}

    @Bean(name = "majorNamedParameterJdbcTemplate")
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(javax.sql.DataSource majorDataSource) {
        return new NamedParameterJdbcTemplate(majorDataSource);
    }
}
