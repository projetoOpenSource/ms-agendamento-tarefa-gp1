package br.com.agendamento.api.constantes;

/**
 * Classe de constantes de token.
 * 
 * @author moriartynho
 */
public class ConstanteToken {

	private ConstanteToken() {
	}

	public static final String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public static final int TOKEN_LENGTH = 6;
	public static final Long TEMPO_DE_EXPIRACAO = 15L;
}
