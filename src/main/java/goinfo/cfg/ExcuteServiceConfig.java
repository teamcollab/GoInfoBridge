package goinfo.cfg;

import goinfo.service.QueryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExcuteServiceConfig {

    @Bean
    public QueryService query(){
        return new QueryService();
    }



}
