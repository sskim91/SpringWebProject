package sskim.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class SampleAdvice {

    private static final Logger logger = LogManager.getLogger(SampleAdvice.class);

    @Before("execution(* sskim.service.MessageService*.*(..))")
    public void startLog(JoinPoint joinPoint) {

        logger.info("----------------");
        logger.info("----------------");
        logger.info(Arrays.toString(joinPoint.getArgs()));
    }

    @Around("execution(* sskim.service.MessageService*.*(..))")
    public Object timeLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();
        logger.info(Arrays.toString(proceedingJoinPoint.getArgs()));

        Object result = proceedingJoinPoint.proceed();

        long endTime = System.currentTimeMillis();
        logger.info(proceedingJoinPoint.getSignature().getName() + ":" + (endTime - startTime));
        logger.info("============================================");

        return result;
    }
}
