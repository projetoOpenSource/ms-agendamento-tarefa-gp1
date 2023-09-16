package br.com.agendamento.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção de erros internos
 * @author moriartynho
 *
 */
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalErrorException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public InternalErrorException(String msg){
		super(msg);
	}

	public InternalErrorException(String msg, Throwable e) {
		super(msg, e);
	}
	

}
