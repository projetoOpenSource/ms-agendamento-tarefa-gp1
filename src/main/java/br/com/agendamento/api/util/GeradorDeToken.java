package br.com.agendamento.api.util;

import java.security.SecureRandom;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.agendamento.api.constantes.ConstanteToken;

/**
 * Classe de serviço de geração de token de validação
 * @author moriartynho
 */
@Service
public class GeradorDeToken {

	

	/**
	 * Método estático que retorna um token alfanumérico aleatório de n caracteres
	 * @return String
	 */
	public static String gerarToken() {
		SecureRandom random = new SecureRandom();
		StringBuilder token = new StringBuilder();

		for (int i = 0; i < ConstanteToken.TOKEN_LENGTH; i++) {
			int randomIndex = random.nextInt(ConstanteToken.CARACTERES.length());
			char randomChar = ConstanteToken.CARACTERES.charAt(randomIndex);
			token.append(randomChar);
		}

		return token.toString();
	}
	
	/**
	 * Método que define a o tempo de expiração de um token
	 * @return
	 */
	public static LocalDateTime atualizarTempoDeExpiracao() {
		return LocalDateTime.now().plusMinutes(ConstanteToken.TEMPO_DE_EXPIRACAO);
	}
}
