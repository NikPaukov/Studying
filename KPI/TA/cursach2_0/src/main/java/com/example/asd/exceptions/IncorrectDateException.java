package com.example.asd.exceptions;

public class IncorrectDateException extends RuntimeException {
    public IncorrectDateException(String date) {
        super(date);
    }
}
