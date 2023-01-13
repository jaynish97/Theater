package com.jpmc.theater.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.HandlerMethod;

@ControllerAdvice
public class TheaterExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleBadRequestException(BadRequestException exception, HandlerMethod handlerMethod){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }



}
