package br.com.agendamento.api.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.agendamento.api.exceptions.MailErrorException;

/**
 * Classe de serviços para envio de emails
 * 
 * @author moriartynho
 */
@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String emailRemetente;

	/**
	 * Método que envia token de confirmação para o email do usuário
	 * @param emailUsuario
	 * @param codigoConfirmacao
	 * @throws MailErrorException
	 */
	public void enviarEmailComToken(String emailUsuario, String codigoConfirmacao) throws MailErrorException {
		StringBuilder corpoDoEmail = new StringBuilder();

		corpoDoEmail.append(
				"Seja bem-vindo ao nosso sistema de agendamento de tarefas. Para continuar, por favor confirme o seu cadastro. Seu código de confirmação é: ");
		corpoDoEmail.append(codigoConfirmacao);

		enviarEmail(emailUsuario, "Boas vindas", corpoDoEmail.toString());
	}

	/**
	 * Método que envia token de recuperaçãp para o email do usuário
	 * @param emailUsuario
	 * @param codigoConfirmacao
	 * @throws MailErrorException
	 */
	public void enviarEmailDeRecuperacao(String emailUsuario, String codigoConfirmacao) throws MailErrorException {
		StringBuilder corpoDoEmail = new StringBuilder();

		corpoDoEmail.append("Use o código ");
		corpoDoEmail.append(codigoConfirmacao);
		corpoDoEmail.append(" para recuperar e alterar sua senha");

		enviarEmail(emailUsuario, "Recuperar/Alterar Senha", corpoDoEmail.toString());

	}

	/**
	 * Método utilizado para enviar email
	 * @param emailDestinatario
	 * @param assuntoDoEmail
	 * @param textoDoEmail
	 * @throws MailErrorException
	 */
	public void enviarEmail(String emailDestinatario, String assuntoDoEmail, String textoDoEmail)
			throws MailErrorException {
		SimpleMailMessage message = new SimpleMailMessage();
		try {
			message.setFrom(emailRemetente);
			message.setTo(emailDestinatario);
			message.setSubject(assuntoDoEmail);
			message.setText(textoDoEmail);
			mailSender.send(message);
		} catch (Exception e) {
			throw new MailErrorException("Ocorreu um erro ao tentar enviar email!");
		}
	}

}
