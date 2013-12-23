
package goinfo.service;

import org.springframework.beans.factory.annotation.Autowired;


public class AmqpService {

    @Autowired
    private QueryService queryService;

	public String receiveMessage(String message) {

        System.out.println("receive :" +message);

        String json = queryService.excuteAndGetJson(message);

        System.out.println("reply   :" +json);
        return json;
    }
}
