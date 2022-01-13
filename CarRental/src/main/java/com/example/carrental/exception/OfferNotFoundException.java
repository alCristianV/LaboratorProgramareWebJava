package com.example.carrental.exception;

public class OfferNotFoundException extends RuntimeException {

    public OfferNotFoundException() {
        super("The offer doesn't exist.");
    }
}