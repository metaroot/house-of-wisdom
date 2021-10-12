package com.houseofwisdom.houseofwisdom.exceptions;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class BadRequest400Exception extends Throwable{
    private static final Logger logger = LoggerFactory.getLogger(BadRequest400Exception.class);

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST) //400
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public void handleBadRequest() {
        logger.error("Error 400, Bad Request. Please check the request");
    }
}
