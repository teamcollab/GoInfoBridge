package goinfo.cfg;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class ApiAspect {


    @Around("execution(java.util.Map goinfo.service.ApiFecadeService.excute(..))")
    public Map logAndHandleException(ProceedingJoinPoint joinPoint) {
        Log logger = LogFactory.getLog(joinPoint.getTarget().getClass());

        logger.info("\n     input: " + joinPoint.getArgs()[0]);

        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            Map resultMap =new HashMap<String, String>();
            resultMap.put("success", false);
            resultMap.put("errorMessage", throwable.getLocalizedMessage());
            logger.error(throwable);

            result = resultMap;

        }

        logger.info("\n    result: " + result);

        return (Map) result;
    }

    @AfterReturning(pointcut = "execution(* goinfo.service.PropertiesHoldService.getQueriesProperty(..))", returning = "result")
    public void logExcuteSql(JoinPoint joinPoint, Object result) {
        Log logger = LogFactory.getLog(joinPoint.getTarget().getClass());
        logger.info("\n       sql: " + result.toString());

    }

}
