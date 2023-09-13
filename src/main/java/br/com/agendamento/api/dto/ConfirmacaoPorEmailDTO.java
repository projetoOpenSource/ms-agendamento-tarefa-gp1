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
public class ConfirmacaoPorEmailDTO {

	@Email(message = "Insira um email válido")
	@Size(max = 100, message = "Email não pode ter mais que 100 caracteres")
	private String email;
	
	@NotBlank(message = "Insira um código válido")
	@Size(max = 6, message = "O Código não pode ser maior que 6 caracteres")
	private String codigoDeConfirmacao;

}
