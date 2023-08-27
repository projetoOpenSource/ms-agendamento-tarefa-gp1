package br.com.agendamento.api.service.cadastro.validacoes;

import org.springframework.stereotype.Component;

import br.com.agendamento.api.dto.CadastroUsuarioDTO;

@Component
public interface ValidacaoCadastro {
	
	void validar(CadastroUsuarioDTO dto);

}
