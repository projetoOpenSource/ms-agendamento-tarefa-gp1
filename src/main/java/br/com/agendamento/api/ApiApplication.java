package br.com.agendamento.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.agendamento.api.exceptions.MailErrorException;

/**
 * Classe starter springboot.
 *
 * @author Salatiel Fiore
 */
@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) throws MailErrorException {
		SpringApplication.run(ApiApplication.class, args);

	}

}
