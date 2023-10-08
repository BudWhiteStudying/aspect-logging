package com.budwhite.studying.aspect.logging.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.Collectors;

import static java.lang.System.currentTimeMillis;

@Aspect
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    @Around("call(* com.budwhite.studying.aspect.logging.service..*(..))")
    public Object whatever(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        if(logger.isDebugEnabled()) {
            if(logger.isTraceEnabled()) {
                logger.trace("{} begins, args are: [{}]",
                        thisJoinPoint.getSignature().getName(),
                        Arrays.stream(thisJoinPoint.getArgs())
                                .map(Object::toString)
                                .collect(Collectors.joining(",")));
            }
            else {
                logger.debug("{} begins", thisJoinPoint.getSignature().getName());
            }
        }
        long startTime = currentTimeMillis();
        Object result = thisJoinPoint.proceed();
        if(logger.isDebugEnabled()) {
            if(logger.isTraceEnabled()) {
                logger.trace("{} ends, execution time {} ms, output is: {}",
                        thisJoinPoint.getSignature().getName(),
                        currentTimeMillis()-startTime,
                        result!=null ? result : ""
                );
            }
            else {
                logger.debug("{} ends", thisJoinPoint.getSignature().getName());
            }
        }
        return result;
    }
}
