package com.sporty.bet.exception;

public class SportyBetException extends RuntimeException {
    public SportyBetException(String message) {
        super(message);
    }

    public SportyBetException(String message, Throwable cause) {
        super(message, cause);
    }
} 