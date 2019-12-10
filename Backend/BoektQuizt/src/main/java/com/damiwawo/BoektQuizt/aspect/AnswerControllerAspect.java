package com.damiwawo.BoektQuizt.aspect;

import com.damiwawo.BoektQuizt.model.helpers.LoggerHelper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@Configuration
public class AnswerControllerAspect {

    private LoggerHelper loggerHelper = new LoggerHelper();

    @Before("execution(* com.damiwawo.BoektQuizt.controller.AnswerController.*(..) )")
    public void checkAccessForAllMethodsOnController(JoinPoint joinPoint){
      loggerHelper.LogBefore(joinPoint);
    }

    @After("execution(* com.damiwawo.BoektQuizt.controller.AnswerController.*(..) )")
    public void checkInfoForAllMethodsAfterExecution(JoinPoint joinPoint){
        loggerHelper.LogAfter(joinPoint);
    }
}
