package goinfo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Service()
@Scope(value = "singleton")
public class PropertiesHoldService {


    private String queriesPotertiesLoacation;

    private Properties queriesProperties;

    @Autowired
    protected PropertiesHoldService(@Value("${properties.queries.location}") String queriesPropertiesLocation) {
        this.queriesPotertiesLoacation = queriesPropertiesLocation;
        queriesProperties = new Properties();
    }

    public Properties getQueriesProperties(){
        try {
            queriesProperties.load(new FileInputStream(queriesPotertiesLoacation));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return queriesProperties;
    }

    public String getQueriesProperty(String propertyName){

        return getQueriesProperties().getProperty(propertyName);
    }
}
