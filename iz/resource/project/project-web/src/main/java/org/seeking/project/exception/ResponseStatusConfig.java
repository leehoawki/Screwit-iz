package org.seeking.{{ project }}.exception;

import org.seeking.{{ project }}.conf.request.WebResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
@RestControllerAdvice
public class ResponseStatusConfig {
    static final Logger LOG = LoggerFactory.getLogger(ResponseStatusConfig.class);

    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        LOG.error("Internal unhandled exception", e);
        WebResponse webResponse = new WebResponse();
        webResponse.setData(null);
        webResponse.setError(500);
        webResponse.setMessage("出错啦>//<努力加载中请稍后再试");
        return webResponse;
    }

    @RequestMapping("/error/404")
    public Object r404() {
        WebResponse webResponse = new WebResponse();
        webResponse.setData(null);
        webResponse.setError(404);
        return webResponse;
    }

    @RequestMapping("/error/400")
    public Object r400() {
        WebResponse webResponse = new WebResponse();
        webResponse.setData(null);
        webResponse.setError(400);
        return webResponse;
    }

    @Configuration
    class ContainerConfig implements ErrorPageRegistrar {

        /**
         * Register pages as required with the given registry.
         *
         * @param registry the error page registry
         */
        @Override
        public void registerErrorPages(ErrorPageRegistry registry) {
            registry.addErrorPages(
                    new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"),
                    new ErrorPage(HttpStatus.BAD_REQUEST, "/error/400")
            );
        }
    }
}
