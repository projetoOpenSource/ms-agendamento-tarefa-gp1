package br.com.agendamento.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioDTO {

	private Long id_usuario;

	private String nome;

	private String email;

	private String senha;

	private Long id_status;

}
