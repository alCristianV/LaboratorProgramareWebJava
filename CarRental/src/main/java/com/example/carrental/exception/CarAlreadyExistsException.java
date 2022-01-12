package com.example.carrental.exception;

public class CarAlreadyExistsException extends RuntimeException {

    public CarAlreadyExistsException() {
        super("There is already a car with the same plate number.");
    }
}