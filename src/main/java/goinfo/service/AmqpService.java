
package goinfo.service;

import org.springframework.beans.factory.annotation.Autowired;


public class AmqpService {

    @Autowired
    private ExcuteService excuteService;

	public String receiveMessage(String message) {

        return excuteService.excute(message);
    }
}
