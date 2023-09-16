package br.com.agendamento.api.service.cadastro;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agendamento.api.dto.RecuperacaoDeSenhaDTO;
import br.com.agendamento.api.dto.SolicitacaoDeRecuperacaoDeSenhaDTO;
import br.com.agendamento.api.exceptions.InternalErrorException;
import br.com.agendamento.api.exceptions.MailErrorException;
import br.com.agendamento.api.exceptions.ValidacaoException;
import br.com.agendamento.api.model.Usuario;
import br.com.agendamento.api.model.UsuarioToken;
import br.com.agendamento.api.repository.UsuarioRepository;
import br.com.agendamento.api.repository.UsuarioTokenRepository;
import br.com.agendamento.api.service.email.EmailService;
import br.com.agendamento.api.util.GeradorDeToken;

/**
 * Classe de serviço relacionada à recuperação de senha do usuário
 * 
 * @author moriartynho
 */
@Service
public class RecuperacaoService {

	@Autowired
	private CadastroService cadastroService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioTokenRepository tokenRepository;

	@Autowired
	private EmailService emailService;

	/**
	 * Método que envia token de recuperação para o usuário
	 * 
	 * @param recuperacaoDTO
	 * @throws ValidacaoException
	 */
	public void solicitarTokenDeRecuperacaoPorEmail(@Valid SolicitacaoDeRecuperacaoDeSenhaDTO recuperacaoDTO)
			throws ValidacaoException {

		if (!cadastroService.verificaSeOEmailEstaCadastrado(recuperacaoDTO.getEmail())) {
			throw new ValidacaoException("Usuario não encontrado na base de dados");
		}

		try {
			Usuario usuario = usuarioRepository.findByEmail(recuperacaoDTO.getEmail());
			pegarTokenPorUsuarioAtualizarEEnviarPorEmail(usuario);
		} catch (Exception e) {
			throw new InternalErrorException("Ocorreu um erro ao tentar acessar o banco de dados");
		}
	}

	public void recuperarEAlterarSenhaComToken(@Valid RecuperacaoDeSenhaDTO recuperacaoDTO) {

		try {
			Usuario usuario = usuarioRepository.findByEmail(recuperacaoDTO.getEmail());
			UsuarioToken token = tokenRepository.findByUsuario(usuario);
			validarRecuperacaoDeSenha(recuperacaoDTO, usuario, token);
			usuario.setSenha(recuperacaoDTO.getSenha());
			usuarioRepository.save(usuario);

		} catch (ValidacaoException | MailErrorException e) {
			throw new InternalErrorException(e.getMessage());
		}

	}

	private void validarRecuperacaoDeSenha(RecuperacaoDeSenhaDTO recuperacaoDTO, Usuario usuario, UsuarioToken token)
			throws ValidacaoException, MailErrorException {

		if (!recuperacaoDTO.getCodigoDeConfirmacao().toUpperCase().equals(token.getCodigoConfirmacao())) {
			throw new ValidacaoException("Token de confirmação inválido");
		}

		if (LocalDateTime.now().isAfter(token.getDataExpiracao())) {
			String novoCodigo = GeradorDeToken.gerarToken();
			token.setCodigoConfirmacao(novoCodigo);
			token.setDataExpiracao(GeradorDeToken.atualizarTempoDeExpiracao());
			tokenRepository.save(token);
			emailService.enviarEmailDeRecuperacao(usuario.getEmail(), novoCodigo);
			throw new ValidacaoException(
					"Seu código de confirmação expirou. Tente novamente. Um novo código será enviado por email");
		}

		if (!recuperacaoDTO.getSenha().equals(recuperacaoDTO.getConfirmarSenha())) {
			throw new ValidacaoException("Senhas não coincidem");
		}

	}

	private void pegarTokenPorUsuarioAtualizarEEnviarPorEmail(Usuario usuario) throws MailErrorException {
		try {
			UsuarioToken token = tokenRepository.findByUsuario(usuario);
			token.setCodigoConfirmacao(GeradorDeToken.gerarToken());
			tokenRepository.save(token);
			emailService.enviarEmailDeRecuperacao(usuario.getEmail(), token.getCodigoConfirmacao());
		} catch (Exception e) {
			throw new InternalErrorException("Ocorreu um erro ao tentar acessar o banco de dados");
		}

	}

}
