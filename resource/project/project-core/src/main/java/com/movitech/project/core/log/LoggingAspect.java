package com.movitech.{{ project }}.core.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    private Logger LOG = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("@annotation(com.movitech.{{ project }}.core.log.Logging)")
    public void annotation() {

    }

    @Around("annotation()")
    public Object execute(ProceedingJoinPoint pjp) throws Throwable {
        try {
            Object object = pjp.proceed();
            LOG.info("function      = " + pjp.getSignature());
            LOG.info("    arguments = " + Arrays.asList(pjp.getArgs()));
            LOG.info("    return    = " + object);
            return object;
        } catch (Throwable throwable) {
            LOG.info("function      = " + pjp.getSignature());
            LOG.info("    arguments = " + pjp.getArgs());
            LOG.info("    exception = " + throwable.getMessage());
            LOG.info("    cause = " + throwable.getCause());
            throw throwable;
        }
    }
}
