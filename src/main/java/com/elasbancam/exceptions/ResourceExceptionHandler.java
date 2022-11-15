package com.elasbancam.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ContaNaoExistenteException.class)
    public ResponseEntity<ErroPadraoExceprion> resourceNotFound(ContaNaoExistenteException e, HttpServletRequest request){
        String error = "Recurso nao encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErroPadraoExceprion err = new ErroPadraoExceprion(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
