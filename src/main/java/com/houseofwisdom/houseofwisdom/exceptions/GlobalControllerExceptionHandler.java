package com.houseofwisdom.houseofwisdom.exceptions;


import com.houseofwisdom.houseofwisdom.HouseofwisdomApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ResponseStatus(HttpStatus.CONFLICT) //409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void handleConflict() {
        logger.error("Error 409, Conflict occured");
    }
}
