package br.com.agendamento.api.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class CadastroUsuarioDTO {
	
	@NotBlank(message = "Nome não pode ser nulo")
	private String nome;
	
	@NotBlank(message = "Email não pode ser nulo")
	private String email;
	
	@NotBlank(message = "Senha não pode ser nulo")
	@Min(value = 6, message = "Senha deve ter pelo menos 6 caracteres")
	private String senha;
	
	@NotBlank(message = "Confirme a senha")
	private String confirmarSenha;

}
