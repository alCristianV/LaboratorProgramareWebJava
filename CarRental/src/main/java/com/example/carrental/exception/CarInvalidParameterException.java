package com.example.carrental.exception;

public class CarInvalidParameterException extends RuntimeException {

    public CarInvalidParameterException(String parameterName) {
        super(parameterName + "has not a valid value");
    }
}