package org.webrise.subscription.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(* org.webrise.subscription.controller..*.*(..))")
    public void controllerMethods() {}

    @Pointcut("execution(* org.webrise.subscription.service..*.*(..))")
    public void serviceMethods() {}

    @Before("controllerMethods()")
    public void logBeforeController(JoinPoint joinPoint) {
        log.info("Вызов контроллера: {} с аргументами: {}", 
                joinPoint.getSignature().toShortString(), 
                Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "controllerMethods()", returning = "result")
    public void logAfterController(JoinPoint joinPoint, Object result) {
        log.info("Контроллер {} вернул: {}", 
                joinPoint.getSignature().toShortString(), 
                result);
    }

    @Around("serviceMethods()")
    public Object logAroundService(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("Начало выполнения сервиса: {}", joinPoint.getSignature().toShortString());
        try {
            long start = System.currentTimeMillis();
            Object result = joinPoint.proceed();
            long executionTime = System.currentTimeMillis() - start;
            log.debug("Сервис {} выполнен за {} мс", joinPoint.getSignature().toShortString(), executionTime);
            return result;
        } catch (Exception e) {
            log.error("Ошибка в сервисе {}: {}", joinPoint.getSignature().toShortString(), e.getMessage());
            throw e;
        }
    }
} 