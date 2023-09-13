package br.com.agendamento.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agendamento.api.dto.CadastroUsuarioDTO;
import br.com.agendamento.api.dto.ConfirmacaoPorEmailDTO;
import br.com.agendamento.api.dto.UsuarioDTO;
import br.com.agendamento.api.exceptions.ValidacaoException;
import br.com.agendamento.api.service.cadastro.CadastroService;
import io.swagger.annotations.Api;

/**
 * 
 * Controller de operações relacionadas ao usuário
 * 
 * @author moriartynho
 */
@Api(value = "Usuario", tags = { "Usuario" })
@RestController
@RequestMapping(value = "/usuarios")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UsuarioController {

	@Autowired
	private CadastroService cadastroService;

	/**
	 * 
	 * Endpoint para cadastrar um usuário
	 * 
	 * @param novoUsuario Recebe um objeto com dados para o cadastro de um novo
	 *                    usuário
	 * @throws ValidacaoException caso ocorra alguma inconsistência nos dados
	 *                            recebidos, é lançada uma exceção de validação
	 */
	@PostMapping()
	public ResponseEntity<UsuarioDTO> cadastrarUsuario(@Valid @RequestBody CadastroUsuarioDTO novoUsuario)
			throws ValidacaoException {
		cadastroService.cadastrarUsuario(novoUsuario);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/validar-cadastro")
	public ResponseEntity<Object> validarCadastroDoUsuario(@RequestBody ConfirmacaoPorEmailDTO confirmacaoDTO) throws ValidacaoException {
		cadastroService.validarCadastroDoUsuario(confirmacaoDTO);
		return ResponseEntity.ok().build();
	}

}
