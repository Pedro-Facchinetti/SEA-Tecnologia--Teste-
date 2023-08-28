package com.pedrofacchinetti.TesteBackEnd.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Global Exception Handler")
@ControllerAdvice
public class GlobalExceptionHandler {

    @ApiOperation(value = "Manipula exceções de recursos não encontrados")
    @ApiResponses(value = {
        @ApiResponse(code = 404, message = "Recurso não encontrado")
    })
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<String> handleApiException(ApiException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_GATEWAY);
    }

    @ApiOperation(value = "Manipula exceções de violações de integridade de dados")
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Violação de integridade de dados, como CPF duplicado")
    })
    @ExceptionHandler(CustomDataIntegrityViolationException.class)
    public ResponseEntity<String> handleCustomDataIntegrityViolationException(CustomDataIntegrityViolationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomConstraintViolationException.class)
    public ResponseEntity<String> handleCustomConstraintViolationException(CustomConstraintViolationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
