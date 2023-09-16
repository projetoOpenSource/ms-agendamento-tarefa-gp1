package br.com.agendamento.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agendamento.api.dto.RecuperacaoDeSenhaDTO;
import br.com.agendamento.api.dto.SolicitacaoDeRecuperacaoDeSenhaDTO;
import br.com.agendamento.api.dto.UsuarioDTO;
import br.com.agendamento.api.exceptions.ValidacaoException;
import br.com.agendamento.api.service.cadastro.RecuperacaoService;
import io.swagger.annotations.Api;

/**
 * 
 * Controller de operações relacionadas à recuperação de conta do usuário
 * 
 * @author moriartynho
 */
@Api(value = "Recuperar", tags = { "Usuario" })
@RestController
@RequestMapping(value = "/recuperar")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RecuperacaoController {
	
	@Autowired
	private RecuperacaoService recuperacaoService;
	
	@PostMapping("/solicitar-token")
	public ResponseEntity<UsuarioDTO> solicitarTokenDeRecuperacao(@Valid @RequestBody SolicitacaoDeRecuperacaoDeSenhaDTO solicitacaoDeRecuperacaoDTO)
			throws ValidacaoException {
		recuperacaoService.solicitarTokenDeRecuperacaoPorEmail(solicitacaoDeRecuperacaoDTO);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/recuperar-senha")
	public ResponseEntity<UsuarioDTO> recuperarEAlterarSenhaComToken(@Valid @RequestBody RecuperacaoDeSenhaDTO recuperacaoDTO){
		recuperacaoService.recuperarEAlterarSenhaComToken(recuperacaoDTO);
		return ResponseEntity.ok().build();
	}
}
