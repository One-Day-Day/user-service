package com.lovecode.system.exception;


public class ConflictException extends RuntimeException {
    public ConflictException() {
        super("Have conflict exception!");
    }

    public ConflictException(String message) {
        super(message);
    }
}
