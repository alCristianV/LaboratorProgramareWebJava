package com.example.carrental.exception;

public class ClientAlreadyExistsException extends RuntimeException {

    public ClientAlreadyExistsException(String property) {
        super("There is already a client with the same " + property);
    }
}