package org.seeking.{{ project }}.logging;

import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Aspect
@Component
public class LoggingAspect {
    private static Logger LOG = LoggerFactory.getLogger(LoggingAspect.class);

    Gson gson = new Gson();

    @Pointcut("execution(* org.seeking.{{ project }}..controller..*(..))")
    public void requestPointcut() {

    }

    @Before("requestPointcut()")
    public void doBefore(JoinPoint joinPoint) {
        // Receives the request and get request content
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();

        String http_method = request.getMethod();
        String line = "Request received, ";
        line += "URL=" + request.getRequestURL().toString() + ", ";
        line += "HTTP_METHOD=" + http_method + ", ";
        line += "CLASS_METHOD=" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + ", ";
        LOG.info(line + "ARGS : " + gson.toJson(joinPoint.getArgs()));
    }
}
