package org.seeking.{{ project }}.exception;


import org.seeking.{{ project }}.base.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseBody
    public Response<String> iae(Exception ex) {
        ex.printStackTrace();
        return Response.error(5000, ex.getMessage());
    }
}