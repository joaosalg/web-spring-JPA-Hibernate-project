package com.springBoot.JPA.webService.resources.exceptions;

import com.springBoot.JPA.webService.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

// CONTROLLER ADVICE INTERCEPTA AS EXCEPTIONS QUE ACONTECEREM PARA QUE ESSE OBJ EXECUTE UM POSSÍVEL TRATAMENTO //
@ControllerAdvice
public class ResourceExceptionHandler {

    // PARA INTERCEPTAR QUALQUER EXCEPTION DO TIPO RESOURCENOTFOUND //
    // return ResponseEntity.status() = MÉTODO PERSONALIZADO //
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
