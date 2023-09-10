package br.com.agendamento.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
<<<<<<< HEAD
 * Classe para mensagem personalizada de erro interno de valor 500.
 * @author leovizeu
 */
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalErrorException extends Exception {
    public InternalErrorException(String msg) {
        super("Erro Interno! " + msg);
    }

}
