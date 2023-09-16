package br.com.agendamento.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolicitacaoDeRecuperacaoDeSenhaDTO {
	
	@NotBlank(message = "Email não pode ser nulo")
	@Email(message = "Insira um email válido")
	@Size(max = 100, message = "Email não pode ter mais que 100 caracteres")
	private String email;

}
