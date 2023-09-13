package br.com.agendamento.api.service.cadastro;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agendamento.api.constantes.ConstanteStatus;
import br.com.agendamento.api.constantes.ConstanteToken;
import br.com.agendamento.api.dto.CadastroUsuarioDTO;
import br.com.agendamento.api.dto.ConfirmacaoPorEmailDTO;
import br.com.agendamento.api.exceptions.InternalErrorException;
import br.com.agendamento.api.exceptions.ValidacaoException;
import br.com.agendamento.api.model.Status;
import br.com.agendamento.api.model.Usuario;
import br.com.agendamento.api.model.UsuarioToken;
import br.com.agendamento.api.repository.UsuarioRepository;
import br.com.agendamento.api.repository.UsuarioTokenRepository;
import br.com.agendamento.api.service.StatusService;
import br.com.agendamento.api.service.email.EmailService;

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
	private StatusService statusService;

	@Autowired
	private UsuarioTokenRepository tokenRepository;

	@Autowired
	private EmailService emailService;

	/**
	 * Método de cadastro de usuário
	 * 
	 * @param dto parâmetro que recebe os dados para cadastrar um novo usuário
	 * @throws ValidacaoException caso ocorra alguma inconsistência nos dados
	 *                            recebidos, é lançada uma exceção de validação
	 */
	public void cadastrarUsuario(@Valid CadastroUsuarioDTO dto) throws ValidacaoException {

		if (verificaSeOEmailJaEstaCadastrado(dto.getEmail())) {
			throw new ValidacaoException("Email já cadastrado");
		}

		if (!dto.getSenha().equals(dto.getConfirmarSenha())) {
			throw new ValidacaoException("Senhas não coincidem");
		}

		Status status = statusService.buscarStatusUsuario(ConstanteStatus.USUARIO_NOVO);
		try {
			Usuario usuario = new Usuario(null, dto.getNome(), dto.getEmail(), dto.getSenha(), status);
			UsuarioToken token = new UsuarioToken(null, GeradorDeToken.generateToken(),
					LocalDateTime.now().plusMinutes(ConstanteToken.TEMPO_DE_EXPIRACAO), usuario);
			usuarioRepository.save(usuario);
			tokenRepository.save(token);
			emailService.enviarEmailComToken(usuario.getEmail(), token.getCodigoConfirmacao());

		} catch (Exception e) {
			throw new InternalErrorException("Ocorreu um erro ao tentar acessar o banco de dados");
		}

	}

	/**
	 * Método de validação de cadastro do usuário
	 * @param confirmacaoDTO
	 * @throws ValidacaoException
	 */
	public void validarCadastroDoUsuario(ConfirmacaoPorEmailDTO confirmacaoDTO) throws ValidacaoException {

		if (!verificaSeOEmailJaEstaCadastrado(confirmacaoDTO.getEmail())) {
			throw new ValidacaoException("Usuario não encontrado na base de dados");
		}

		try {

			Usuario usuario = usuarioRepository.findByEmail(confirmacaoDTO.getEmail());
			UsuarioToken token = tokenRepository.findByUsuario(usuario);

			if (LocalDateTime.now().isAfter(token.getDataExpiracao())) {
				String novoCodigo = GeradorDeToken.generateToken();
				token.setCodigoConfirmacao(novoCodigo);
				tokenRepository.save(token);
				emailService.enviarEmailComToken(usuario.getEmail(), token.getCodigoConfirmacao());
				throw new ValidacaoException(
						"Seu código de confirmação expirou. Tente novamente. Um novo código seá enviado por email");
			}

			if (!confirmacaoDTO.getCodigoDeConfirmacao().toUpperCase().equals(token.getCodigoConfirmacao())) {
				throw new ValidacaoException(
						"O código informado está incorreto. Por favor verifique seu email, e informe o código recebido");
			}

			Status status = statusService.buscarStatusUsuario(ConstanteStatus.USUARIO_ATIVO);
			usuario.setStatus(status);
			usuarioRepository.save(usuario);

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
