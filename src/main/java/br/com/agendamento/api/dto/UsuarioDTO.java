package br.com.agendamento.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Classe de transferência que retorna os dados de um usuário
 * @author moriartynho
 *
 */
@Data
@AllArgsConstructor
public class UsuarioDTO {

	private Long idUsuario;

	@NotBlank(message = "Nome não pode ser nulo")
	@Max(value = 50, message = "Nome não pode ter mais que 50 caracteres")
	private String nome;

	@Email(message = "Insira um email válido")
	@NotBlank(message = "Email não deve ser nulo")
	private String email;

	@NotBlank(message = "Insira uma senha válida")
	private String senha;

	private Long idStatus;

}
