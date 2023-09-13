package br.com.agendamento.api.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario_token")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario_token")
	private Long id;
	
	@Column(name = "codigo_confirmacao")
	private String codigoConfirmacao;
	
	@Column(name = "data_expiracao")
	private LocalDateTime dataExpiracao;
	
	@OneToOne
	@JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
	private Usuario usuario;
	
}
