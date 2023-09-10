package br.com.agendamento.api.handle;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
 /**
  * 
  * @author moriartynho
  *
  */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ResponseMessageError {

	private Instant timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;

}
