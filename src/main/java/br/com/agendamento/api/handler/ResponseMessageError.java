package br.com.agendamento.api.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * @author leovizeu
 */
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
