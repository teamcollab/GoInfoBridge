
package goinfo.web;

import goinfo.service.ApiFecadeService;
import goinfo.service.ConvertService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;


public class AmqpController {

    @Autowired
    private ApiFecadeService apiFecadeService;
    @Autowired
    private ConvertService convertService;

	public String receiveMessage(String message) {
        Map paramsMap = convertService.stringToMap(message);
        Map result =apiFecadeService.excute(paramsMap);
        String jsonString =convertService.mapToJsonString(result);
        return jsonString;
    }
}
