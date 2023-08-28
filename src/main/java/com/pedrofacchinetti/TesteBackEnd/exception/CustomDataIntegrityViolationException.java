package com.pedrofacchinetti.TesteBackEnd.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.swagger.annotations.ApiModel;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@ApiModel(description = "Exceção lançada quando uma violação de restrição ocorre")
public class CustomDataIntegrityViolationException extends RuntimeException {
    public CustomDataIntegrityViolationException(String message) {
        super(message);
    }
}
