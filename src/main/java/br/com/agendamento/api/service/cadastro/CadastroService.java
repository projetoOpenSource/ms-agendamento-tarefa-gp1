package br.com.agendamento.api.service.cadastro;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agendamento.api.dto.CadastroUsuarioDTO;
import br.com.agendamento.api.exceptions.InternalErrorException;
import br.com.agendamento.api.exceptions.ValidacaoException;
import br.com.agendamento.api.model.Usuario;
import br.com.agendamento.api.repository.StatusRepository;
import br.com.agendamento.api.repository.UsuarioRepository;

/**
 * Classe de serviço responsável por cadastrar um usuário
 * 
 * @author moriartynho
 */
@Service
public class CadastroService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private StatusRepository statusRepository;

	/**
	 * Método de cadastro de usuário
	 * @param dto 
	 * 			parâmetro que recebe os dados para cadastrar um novo usuário
	 * @throws ValidacaoException 
	 * 			caso ocorra alguma inconsistência nos dados recebidos, é lançada uma exceção de validação
	 */
	public void cadastrarUsuario(@Valid CadastroUsuarioDTO dto) throws ValidacaoException {
		
		if (verificaSeOEmailJaEstaCadastrado(dto.getEmail())) {
			throw new ValidacaoException("Email já cadastrado");
		}
		
		if (!dto.getSenha().equals(dto.getConfirmarSenha())) {
			throw new ValidacaoException("Senhas não coincidem");
		}
		
		try {
			Usuario obj = new Usuario(null, dto.getNome(), dto.getEmail(), dto.getSenha(), statusRepository.getOne(1L));
			usuarioRepository.save(obj);
		} catch (Exception e) {
			throw new InternalErrorException("Ocorreu um erro ao tentar acessar o banco de dados");
		}
		
	}
	
	
	public boolean verificaSeOEmailJaEstaCadastrado(String email) {
		try {
			return usuarioRepository.existsByEmail(email);
		} catch (Exception e) {
			throw new InternalErrorException("Ocorreu um erro interno");
		}
	}

}
