package br.com.agendamento.api.service.cadastro.validacoes;

import org.springframework.stereotype.Component;

import br.com.agendamento.api.dto.CadastroUsuarioDTO;

@Component
public class ValidarSeSenhasCoincidem implements ValidacaoCadastro {

	@Override
	public void validar(CadastroUsuarioDTO dto) {
		if(!dto.getSenha().equals(dto.getConfirmarSenha())) {
			throw new RuntimeException("Senhas não coincidem");
		}
		
	}

}
