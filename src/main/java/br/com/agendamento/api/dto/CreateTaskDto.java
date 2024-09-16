package br.com.agendamento.api.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Classe para limites de caracteres.
 * @author leovizeu
 */
@Getter
@Setter
public class CreateTaskDto {

    @NotBlank(message = "O título não pode estar em branco!")
    @Size(max = 100, message = "O número máximo de caracteres é de 100!")
    private String titulo;

    @Size(max = 255, message = "O número máximo de caracteres é de 255!")
    private String descricao;


    private LocalDateTime dataVencimento;
}
