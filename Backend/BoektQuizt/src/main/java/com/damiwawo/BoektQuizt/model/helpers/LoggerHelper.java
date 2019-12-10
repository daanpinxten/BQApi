package com.damiwawo.BoektQuizt.model.helpers;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public  class LoggerHelper {

    private  Logger log;
        public LoggerHelper(){
            log = LoggerFactory.getLogger(this.getClass());
        }

    public  void LogBefore(JoinPoint joinPoint){
        log.info("Entering in Method :  " + joinPoint.getSignature().getName());
        log.info("Class Name :  " + joinPoint.getSignature().getDeclaringTypeName());
        log.info("Arguments :  " + Arrays.toString(joinPoint.getArgs()));
        log.info("Target class : " + joinPoint.getTarget().getClass().getName());
        log.info("Check for user Access");
        log.info("Allowed execution for {}" , joinPoint.getSignature().getName());
    }
    public  void LogAfter(JoinPoint joinPoint){
        log.info("After Method execution");
        log.info("Allowed execution for {}", joinPoint.getSignature().getName());
    }
}
