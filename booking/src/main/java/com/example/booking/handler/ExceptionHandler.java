package com.example.booking.handler;

import com.example.booking.dto.ErrorResponse;
import com.example.booking.exception.AlreadyExistsException;
import com.example.booking.exception.BadRequestException;
import com.example.booking.exception.BookingException;
import com.example.booking.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.rmi.AlreadyBoundException;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class ExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound(Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        return ex.getMessage();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String badRequest(Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        return ex.getMessage();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userExists(Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        return ex.getMessage();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BookingException.class)
    @ResponseStatus(HttpStatus.LOCKED)
    public String available(Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        return ex.getMessage();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> notValid(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<String> errorMessages = bindingResult.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        String errorMessage = String.join("; ", errorMessages);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new com.example.booking.dto.ErrorResponse(errorMessage));
    }
}