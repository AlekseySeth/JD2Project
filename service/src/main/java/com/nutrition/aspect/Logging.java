package com.nutrition.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author a.shestovsky
 */
@Aspect
public class Logging {

    private final Logger LOGGER;

    @Autowired
    public Logging(Logger LOGGER) {
        this.LOGGER = LOGGER;
    }

    @Pointcut("@within(LoggerCall)")
    public void loggerCall() {}


    @Pointcut("execution(* com.nutrition.marketing.*.*(..))")
    public void marketing() {}

    @Pointcut("execution(* com.nutrition.order.*.*(..))")
    public void order() {}

    @Pointcut("execution(* com.nutrition.product.*.*(..))")
    public void product() {}

    @Pointcut("execution(* com.nutrition.user.*.*(..))")
    public void user() {}

    @Around("marketing() || order() || product() || user()")
    public Object logService(ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();

        LOGGER.info("Call method " + methodName + " with args " + methodArgs.toString());

        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        LOGGER.info("Method " + methodName + " returns " + result);

        return result;
    }

}
