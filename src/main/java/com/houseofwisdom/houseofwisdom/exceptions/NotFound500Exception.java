package com.houseofwisdom.houseofwisdom.exceptions;


import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;


@ControllerAdvice
public class NotFound500Exception extends NoSuchElementException {
    private static final Logger logger = LoggerFactory.getLogger(NotFound500Exception.class);

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT) //204
    @ExceptionHandler(NoSuchElementException.class)
    public void handleNoContent() {
        logger.error("Error 500, No Content");
    }
}
