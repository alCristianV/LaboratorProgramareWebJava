package com.example.carrental.exception;

public class OfferInvalidFilterException extends RuntimeException {

    public OfferInvalidFilterException(String filterName) {
        super(filterName + "has not a valid value");
    }
}