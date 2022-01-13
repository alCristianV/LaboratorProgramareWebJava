package com.example.carrental.exception;

public class OfferMinimumDaysException extends RuntimeException {

    public OfferMinimumDaysException() {
        super("The offer minimum rental days are not satisfied.");
    }
}