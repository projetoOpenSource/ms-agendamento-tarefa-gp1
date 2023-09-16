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

	public void enviarEmailComToken(String emailUsuario, String codigoConfirmacao) throws MailErrorException {
		enviarEmail(emailUsuario, "Boas vindas",
				"Seja bem-vindo ao nosso sistema de agendamento de tarefas. Para continuar, por favor confirme o seu cadastro. Seu código de confirmação é: \n"
						+ codigoConfirmacao);
	}

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
