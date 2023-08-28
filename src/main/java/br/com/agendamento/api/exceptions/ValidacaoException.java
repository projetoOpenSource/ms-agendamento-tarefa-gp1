package br.com.agendamento.api.exceptions;

public class ValidacaoException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public ValidacaoException(String msg) {
		super("Erro de validação: " + msg);
	}

}
