package goinfo.filter;

import goinfo.service.ConvertService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class ApiFilter {

        @Autowired
        ConvertService convertService;

        @Around("execution(* *..*ApiService.*(..))")
        public String logAndHandleException(ProceedingJoinPoint joinPoint) {
        Log logger = LogFactory.getLog(joinPoint.getTarget().getClass());


        logger.info("\n    input: " + joinPoint.getArgs()[0]);

        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            Map resultMap =new HashMap<String, String>();
            resultMap.put("success", false);
            resultMap.put("errorMessage", throwable.getLocalizedMessage());
            logger.error(throwable);

            result = convertService.mapToJsonString(resultMap);

        }

        logger.info("\n    result: " + result);





        return result.toString();
    }



}
