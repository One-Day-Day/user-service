package com.lovecode.system.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("object not found");
    }

    public NotFoundException(String element) {
        super(element + " not found");
    }
}
