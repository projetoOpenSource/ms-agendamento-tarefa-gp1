package br.com.agendamento.api.exceptions;

/**
 * Classe de exceção para Envio de emails
 * @author moriartynho
 */
public class MailErrorException extends Exception {
	private static final long serialVersionUID = 1L;

	public MailErrorException(String msg) {
		super(msg);
	}
}
