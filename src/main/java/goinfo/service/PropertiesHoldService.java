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


    private static String queriesPotertiesLoacation;

    private static Properties queriesProperties;

    @Autowired
    protected PropertiesHoldService(@Value("${properties.queries.location}") String queriesPotertiesLoacation) {
        this.queriesPotertiesLoacation = queriesPotertiesLoacation;
        queriesProperties = new Properties();
    }

    public static Properties getQueriesProperties(){
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
