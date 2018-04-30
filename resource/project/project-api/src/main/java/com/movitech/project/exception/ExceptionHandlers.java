package com.movitech.{{ project }}.exception;


import com.movitech.{{ project }}.base.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseBody
    public Response<String> iae(Exception ex) {
        return Response.error(5000, ex.getMessage());
    }
}