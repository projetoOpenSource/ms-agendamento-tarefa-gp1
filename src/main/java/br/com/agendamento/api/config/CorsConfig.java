package br.com.agendamento.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Classe de configuração do cors.
 *
 * @author Salatiel Fiore
 */
@Configuration
public class CorsConfig {

	/**
	 * Configuração de CORS
	 *
	 * @return {@link WebMvcConfigurer}
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedHeaders("*").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS",
						"HEAD", "TRACE", "CONNECT");
			}
		};
	}

}
