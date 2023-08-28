package br.com.agendamento.api.exceptions;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessageError {

	private Instant timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;

}
