package br.com.agendamento.api.exceptions;
/**
 * Exceção de validação
 * @author moriartynho
 *
 */
public class ValidacaoException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public ValidacaoException(String msg) {
		super(msg);
	}

}
