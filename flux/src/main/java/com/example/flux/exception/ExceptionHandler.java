package com.example.flux.exception;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);
    @org.springframework.web.bind.annotation.ExceptionHandler(UpdateErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String ss(Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        return ex.getMessage();
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(AddObserverException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String dd(Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        return ex.getMessage();
    }
}
