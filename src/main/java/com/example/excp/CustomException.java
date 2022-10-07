package com.example.excp;

public class CustomException extends RuntimeException {
    public CustomException(String msg) {
        super(msg);
    }
}