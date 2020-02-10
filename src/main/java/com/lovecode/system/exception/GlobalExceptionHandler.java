package com.lovecode.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.SignatureException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity handleUserNameAlreadyExistException(ConflictException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT.value())
                .body(e.getMessage());
    }

    @ExceptionHandler(value = SignatureException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity handleSignatureException(SignatureException e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED.value())
                .body(e.getMessage());
    }
}
