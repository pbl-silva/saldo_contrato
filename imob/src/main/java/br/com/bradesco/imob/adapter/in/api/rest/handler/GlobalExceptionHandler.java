package br.com.bradesco.imob.adapter.in.api.rest.handler;

import br.com.bradesco.imob.application.exception.InvalidContractNumberException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidContractNumberException.class)
    public ResponseEntity<Void> handleInvalidContractNumber(
            InvalidContractNumberException exception
    ) {
        return ResponseEntity.badRequest().build();
    }
}