package br.com.agendamento.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "status")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Status {

	@Id
	@Column(name="id_status")
	private Long idStatus;
	
	@Column(name = "tipo_status")
	private String tipoStatus;
	
	private String status;
}
