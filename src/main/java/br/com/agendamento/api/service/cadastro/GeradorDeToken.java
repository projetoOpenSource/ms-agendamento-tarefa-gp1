package br.com.agendamento.api.service.cadastro;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

import br.com.agendamento.api.constantes.ConstanteToken;

@Service
public class GeradorDeToken {

	

	public static String generateToken() {
		SecureRandom random = new SecureRandom();
		StringBuilder token = new StringBuilder();

		for (int i = 0; i < ConstanteToken.TOKEN_LENGTH; i++) {
			int randomIndex = random.nextInt(ConstanteToken.CARACTERES.length());
			char randomChar = ConstanteToken.CARACTERES.charAt(randomIndex);
			token.append(randomChar);
		}

		return token.toString();
	}
}
