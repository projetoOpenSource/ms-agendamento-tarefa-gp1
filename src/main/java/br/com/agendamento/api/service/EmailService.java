package br.com.agendamento.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.agendamento.api.dto.EnvioDeEmailDTO;
import br.com.agendamento.api.exceptions.MailErrorException;

/**
 * Classe de serviços para envio de emails
 * @author moriartynho
 */
@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String emailRemetente;

	public void enviarEmail(EnvioDeEmailDTO envioDeEmailDTO) throws MailErrorException {
		SimpleMailMessage message = new SimpleMailMessage();

		try {
			message.setFrom(emailRemetente);
			message.setTo(envioDeEmailDTO.getEmail());
			message.setSubject("Email de recuperação");
			message.setText("Esse é um email de teste enviada às ");
			mailSender.send(message);
		} catch (Exception e) {
			throw new MailErrorException("Ocorreu um erro ao tentar enviar email!");
		}
	}

}
