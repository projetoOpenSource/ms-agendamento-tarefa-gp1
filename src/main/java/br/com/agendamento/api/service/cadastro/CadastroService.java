package br.com.agendamento.api.service.cadastro;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agendamento.api.dto.CadastroUsuarioDTO;
import br.com.agendamento.api.model.Usuario;
import br.com.agendamento.api.repository.UsuarioRepository;
import br.com.agendamento.api.service.cadastro.validacoes.ValidacaoCadastro;

/**
 * Classe de serviço responsável por cadastrar um usuário
 * @author moriartynho
 */
@Service
public class CadastroService {

	@Autowired
	private UsuarioRepository repository;

	private List<ValidacaoCadastro> validacoes;

	public CadastroService(List<ValidacaoCadastro> validacoes) {
		this.validacoes = validacoes;
	}

	public void cadastrarUsuario(@Valid CadastroUsuarioDTO dto) {
		this.validacoes.forEach(v -> v.validar(dto));
		Usuario obj = new Usuario(null, dto.getNome(), dto.getEmail(), dto.getSenha(), 1L);
		repository.save(obj);
	}

}
