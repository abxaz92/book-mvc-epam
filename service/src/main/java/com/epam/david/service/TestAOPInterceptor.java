package com.epam.david.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by David_Chaava on 5/22/2017.
 */
@Aspect
@Component
public class TestAOPInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(TestAOPInterceptor.class);

    @Around("execution(* com.epam.david.service.BookService.getById(..))")
    public void interceptMethod(ProceedingJoinPoint joinPoint) {
        try {
            logger.warn("Intercept and invoke...");
            logger.warn("args : {}", Arrays.asList(joinPoint.getArgs()));
            logger.warn("kind : {}", joinPoint.getKind());
            logger.warn("signature : {}", joinPoint.getSignature());
            logger.warn("source location : {}", joinPoint.getSourceLocation());
            logger.warn("static part : {}", joinPoint.getStaticPart());
            logger.warn("target : {}", joinPoint.getTarget());
            joinPoint.proceed();
            logger.warn("Intercepted!!!");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
