package com.lovecode.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.SignatureException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = ConflictException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleUserNameAlreadyExistException(ConflictException e) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
    @ExceptionHandler(value = SignatureException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleSignatureException(SignatureException e) {
        return new ErrorMessage(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    }
}
