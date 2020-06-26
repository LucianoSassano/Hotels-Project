package com.example.demo.exception;

public class notFoundException extends RuntimeException {

    public notFoundException(String message) {
        super(message);
    }
}