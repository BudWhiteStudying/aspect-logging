package com.budwhite.studying.aspect.logging.aspect;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

    private final Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    @Around("call(* com.budwhite.studying.aspect.logging.service..*(..))")
    public Object logBeforeAndAfterExecution(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        if(logger.isDebugEnabled()) {
            if(logger.isTraceEnabled()) {
                logger.trace("{} begins, args are: [{}]",
                        thisJoinPoint.getSignature().getName(),
                        Arrays.stream(thisJoinPoint.getArgs())
                                .map(this::representObject)
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
                        result!=null ? representObject(result) : ""
                );
            }
            else {
                logger.debug("{} ends", thisJoinPoint.getSignature().getName());
            }
        }
        return result;
    }

    private String representObject(Object object) {
        if(object==null) {
            return "null";
        }
        else if(object.getClass().isPrimitive()) {
            return object.toString();
        }
        else {
            try {
                return gson.toJson(object);
            }
            catch (Exception e) {
                if (logger.isTraceEnabled()) {
                    logger.trace("Could not represent object {}", object, e);
                }
                return object.toString();
            }
        }
    }
}
