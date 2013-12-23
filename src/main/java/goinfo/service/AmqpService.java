
package goinfo.service;

import org.springframework.beans.factory.annotation.Autowired;


public class AmqpService {

    @Autowired
    private QueryApiService queryApiService;

	public String receiveMessage(String message) {

        String json = queryApiService.excuteAndGetJson(message);

        return json;
    }
}
