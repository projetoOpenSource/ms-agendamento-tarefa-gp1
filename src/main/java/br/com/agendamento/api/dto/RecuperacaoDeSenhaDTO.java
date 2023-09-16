package br.com.agendamento.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe de transferência de dados para recuperação de senha através de token
 * @author moriartynho
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecuperacaoDeSenhaDTO {
	
	@NotBlank(message = "Email não pode ser nulo")
	@Email(message = "Insira um email válido")
	@Size(max = 100, message = "Email não pode ter mais que 100 caracteres")
	private String email;
	
	@NotBlank(message = "Senha não pode ser nulo")
	@Size(min = 6, message = "Senha deve ter pelo menos 6 caracteres")
	private String senha;
	
	@NotBlank(message = "Confirme a senha")
	@Size(min = 6, message = "Senha deve ter pelo menos 6 caracteres")
	private String confirmarSenha;
	
	@NotBlank(message = "Insira um código válido")
	@Size(max = 6, message = "O Código não pode ser maior que 6 caracteres")
	private String codigoDeConfirmacao;

}
