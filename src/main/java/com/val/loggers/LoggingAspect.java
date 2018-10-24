package com.val.loggers;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* *.logEvent(..))")
    private void allLogEventMethods() {}

    @Before("allLogEventMethods()")
    public void printBefore(JoinPoint joinPoint) {
        System.out.println("//// Aspect " + joinPoint.getTarget().getClass().getName() + " /////");
    }
}
