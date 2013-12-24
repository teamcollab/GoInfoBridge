package goinfo.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class ApiFilter {


    @Around("execution(java.util.Map goinfo.service.ApiFecadeService.excute(..))")
    public Map logAndHandleException(ProceedingJoinPoint joinPoint) {
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

            result = resultMap;

        }

        logger.info("\n    result: " + result);

        return (Map) result;
    }

//    @Before("execution(* goinfo.service.ApiFecadeService.excute(..))")
//    public void logInput(JoinPoint joinPoint) {
//        Log logger = LogFactory.getLog(joinPoint.getTarget().getClass());
//
//        logger.info("\n    input: " + joinPoint.getArgs()[0]);
//
//    }
//
//    @AfterReturning(pointcut = "execution(* goinfo.service.ApiFecadeService.excute(..))", returning = "result")
//    public void logOutput(JoinPoint joinPoint, Object result) {
//        Log logger = LogFactory.getLog(joinPoint.getTarget().getClass());
//
//        logger.info("\n    result: " + result.toString());
//
//    }

}