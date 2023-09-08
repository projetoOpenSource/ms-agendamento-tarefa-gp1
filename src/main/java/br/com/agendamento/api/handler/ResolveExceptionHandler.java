package br.com.agendamento.api.handler;

import br.com.agendamento.api.exceptions.InternalErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;
import java.time.Instant;

/**
 * Classe para tratamento de exceções de bad request.
 * @author leovizeu
 */
@ControllerAdvice
public class ResolveExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ResponseMessageError> handleValidation(ValidationException e, HttpServletRequest request) {
        return popularResponseMessageError(e, HttpStatus.BAD_REQUEST.value(), "Erro de validação: " + e.getMessage(), request);
    }

    @ExceptionHandler(InternalErrorException.class)
    public ResponseEntity<ResponseMessageError> handleInternal(InternalErrorException e, HttpServletRequest request) {
        return popularResponseMessageError(e, HttpStatus.BAD_REQUEST.value(), "Erro Interno: " + e.getMessage(), request);
    }

    private static ResponseEntity<ResponseMessageError> popularResponseMessageError(Exception e, Integer status, String titulo,
                                                                                    HttpServletRequest request) {
        ResponseMessageError err = new ResponseMessageError();
        err.setTimestamp(Instant.now());
        err.setStatus(status);
        err.setError(titulo);
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }


}
