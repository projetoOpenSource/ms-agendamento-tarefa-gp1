package br.com.agendamento.api.exceptions;

/**
 * Classe de exceção para Envio de emails
 * @author moriartynho
 */
public class MailErrorException extends Exception {

	public MailErrorException(String msg) {
		super(msg);
	}
}
