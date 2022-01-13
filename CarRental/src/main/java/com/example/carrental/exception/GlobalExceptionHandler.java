package com.example.carrental.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ClientNotFoundException.class,
            ClientAlreadyExistsException.class,
            CarAlreadyExistsException.class,
            CarInvalidParameterException.class,
            CarNotFoundException.class,
            InvalidUpdateRequestException.class,
            MethodArgumentNotValidException.class,
            OfferInvalidFilterException.class,
            OfferNotFoundException.class,
            OfferNotAvailableException.class,
            OfferMinimumDaysException.class})
    public ResponseEntity handle(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}

