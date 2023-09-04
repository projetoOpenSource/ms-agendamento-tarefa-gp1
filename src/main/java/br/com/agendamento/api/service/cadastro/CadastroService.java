package br.com.agendamento.api.service.cadastro;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agendamento.api.dto.CadastroUsuarioDTO;
import br.com.agendamento.api.exceptions.ValidacaoException;
import br.com.agendamento.api.model.Status;
import br.com.agendamento.api.model.Usuario;
import br.com.agendamento.api.repository.UsuarioRepository;

/**
 * Classe de serviço responsável por cadastrar um usuário
 * 
 * @author moriartynho
 */
@Service
public class CadastroService {

	@Autowired
	private UsuarioRepository repository;

	public void cadastrarUsuario(@Valid CadastroUsuarioDTO dto) throws ValidacaoException {
		
		if (repository.existsByEmail(dto.getEmail())) {
			throw new ValidacaoException("Email já cadastrado");
		}
		
		if (!dto.getSenha().equals(dto.getConfirmarSenha())) {
			throw new ValidacaoException("Senhas não coincidem");
		}
		
		Usuario obj = new Usuario(null, dto.getNome(), dto.getEmail(), dto.getSenha(), new Status());
		repository.save(obj);
	}

}
