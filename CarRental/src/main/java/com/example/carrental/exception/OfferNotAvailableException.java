package com.example.carrental.exception;

public class OfferNotAvailableException extends RuntimeException {

    public OfferNotAvailableException() {
        super("The offer is not available in the specified period.");
    }
}