
package goinfo.service;

import org.springframework.beans.factory.annotation.Autowired;


public class AmqpService {

    @Autowired
    private QueryApiService queryApiService;

	public String receiveMessage(String message) {

        System.out.println("receive :" +message);

        String json = queryApiService.excuteAndGetJson(message);

        System.out.println("reply   :" +json);
        return json;
    }
}
