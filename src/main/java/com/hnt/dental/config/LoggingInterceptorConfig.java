package com.hnt.dental.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LoggingInterceptorConfig {
    @Pointcut("execution(* com.hnt.dental.controller.*.*(..))")
    public void logController() {
    }

    @Before("logController()")
    public void before(JoinPoint joinPoint) {
        log.info("Start : {}-{}()", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName());
    }

    @After("logController()")
    public void after(JoinPoint joinPoint) {
        log.info("End : {}-{}()", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "logController()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("Error : {}-{}()", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName());
        log.error("Error Message : {}", e.getMessage());
    }

    @AfterReturning("logController()")
    public void afterReturning(JoinPoint joinPoint) {
        log.info("Success : {}-{}()", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName());
    }
}