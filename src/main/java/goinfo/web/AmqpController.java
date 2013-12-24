
package goinfo.web;

import goinfo.service.ApiFecadeService;
import org.springframework.beans.factory.annotation.Autowired;


public class AmqpController {

    @Autowired
    private ApiFecadeService apiFecadeService;

	public String receiveMessage(String message) {
        return apiFecadeService.excute(apiFecadeService, message);
    }
}
