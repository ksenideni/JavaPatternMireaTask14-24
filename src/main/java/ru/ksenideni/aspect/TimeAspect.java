package ru.ksenideni.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class TimeAspect {
    @Pointcut("execution(* ru.ksenideni.item.ItemService.*(..))")
    private void itemServicePointcut() {
    }

    @Pointcut("execution(* ru.ksenideni.order.OrderService.*(..))")
    private void orderServicePointcut() {
    }

    @Pointcut("itemServicePointcut() || orderServicePointcut()")
    private void services() {
    }

    @Around("services()")
    public Object serviceExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();

        long endTime = System.currentTimeMillis();
        log.info(proceedingJoinPoint.getSignature().getName() + " completed in " + (endTime - startTime) + "ms");
        return result;
    }


}
