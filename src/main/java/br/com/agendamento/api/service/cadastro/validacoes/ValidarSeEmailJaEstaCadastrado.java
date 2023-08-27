package br.com.agendamento.api.service.cadastro.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.agendamento.api.dto.CadastroUsuarioDTO;
import br.com.agendamento.api.repository.UsuarioRepository;

@Component
public class ValidarSeEmailJaEstaCadastrado implements ValidacaoCadastro {

	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public void validar(CadastroUsuarioDTO dto) {
		if(repository.findByEmail(dto.getEmail()) != null) {
			throw new RuntimeException("Email já cadastrado");
		}
		
	}

}
