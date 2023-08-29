package br.com.agendamento.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long id_Usuario;
	
	@NotBlank(message = "Nome não pode ser nulo")
	@Max(value = 50, message = "Nome não pode ter mais que 50 caracteres")
	private String nome;
	
	@Email(message = "Insira um email válido")
	private String email;
	
	@NotBlank(message = "Insira uma senha válida")
	private String senha;
	
	@NotNull
	@Column(name = "id_Status")
	private Long id_Status;

}
