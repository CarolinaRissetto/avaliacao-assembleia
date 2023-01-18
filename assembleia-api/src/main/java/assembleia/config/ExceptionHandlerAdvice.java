package assembleia.config;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import assembleia.exception.DomainException;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity handleException(DomainException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }
}