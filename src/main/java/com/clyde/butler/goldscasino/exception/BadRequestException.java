package com.clyde.butler.goldscasino.exception;

public class BadRequestException extends Exception {
    public BadRequestException() {
        super("Error - Please check passed values and try again.");
    }

    public BadRequestException(String message) {
        super(message);
    }
}

