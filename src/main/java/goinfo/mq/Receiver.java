
package goinfo.mq;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import goinfo.cfg.ExcuteServiceConfig;
import goinfo.service.ExcuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Receiver {

    @Autowired
    private Environment env;

    @Autowired
    private JdbcTemplate jdbcTemplate;

	public String receiveMessage(Object message) {

        System.out.println(jdbcTemplate);
        System.out.println(env);

        HashMap<String, Object> map = new HashMap<String,Object>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            map = mapper.readValue(message.toString(),
                    new TypeReference<HashMap<String,Object>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        String action = map.get("action").toString();

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ExcuteServiceConfig.class);
        ExcuteService excuteService = context.getBean(action, ExcuteService.class);
        excuteService.setEnv(env);
        excuteService.setJdbcTemplate(jdbcTemplate);

        Map result = excuteService.excute(map);


        return result.toString();

    }
}
