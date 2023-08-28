package com.pedrofacchinetti.TesteBackEnd.exception;

public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }
}
