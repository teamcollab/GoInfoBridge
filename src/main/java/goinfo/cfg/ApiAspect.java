package goinfo.cfg;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class ApiAspect {

    @Around("execution(* goinfo.service.DataSourceSwichService.*(..))")
    public Object dataSourceSwichHandleException(ProceedingJoinPoint joinPoint) {

        Log logger = LogFactory.getLog(joinPoint.getTarget().getClass());

        if(joinPoint.getArgs()[0].equals(""))
            logger.info("\nconnectname not set, use major connection ");
        else logger.info("\nconnectname: " + joinPoint.getArgs()[0]);

        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        Assert.notNull(result, "查無 " + joinPoint.getArgs()[0] + " 連線資訊");

        return result;

    }

    @Around("execution(java.util.Map goinfo.service.ApiFecadeService.excute(..))")
    public Map serviceHandleException(ProceedingJoinPoint joinPoint) {
        Log logger = LogFactory.getLog(joinPoint.getTarget().getClass());

        logger.info("\n     input: " + joinPoint.getArgs()[0]);

        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            Map resultMap =new HashMap<String, Object>();
            resultMap.put("success", false);
            resultMap.put("errorMessage", throwable.getLocalizedMessage());
            logger.error(throwable);

            result = resultMap;

        }

        logger.info("\n    result: " + result);

        return (Map) result;
    }

    @AfterReturning(pointcut = "execution(* goinfo.service.PropertiesHoldService.getQueriesProperty(..))", returning = "result")
    public void logExecuteSql(JoinPoint joinPoint, Object result) {
        Log logger = LogFactory.getLog(joinPoint.getTarget().getClass());
        logger.info("\n       sql: " + result);

    }

}
