package org.seeking.{{ project }}.exception;


import org.seeking.{{ project }}.controller.common.WebResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {
    static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlers.class);

    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        LOG.error("Internal unhandled exception", e);
        WebResponse webResponse = new WebResponse();
        webResponse.setData(null);
        webResponse.setError(500);
        webResponse.setMessage("出错啦>//<努力加载中请稍后再试");
        return webResponse;
    }
}