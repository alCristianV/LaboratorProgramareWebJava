package com.example.carrental.exception;

public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException() {
        super("The car doesn't exist.");
    }
}