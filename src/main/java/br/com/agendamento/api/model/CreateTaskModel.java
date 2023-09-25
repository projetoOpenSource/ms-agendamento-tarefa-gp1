package br.com.agendamento.api.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Classe de modelo de negócio.
 * @author leovizeu
 */
@Entity
@Table (name = "agendamento_tarefa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskModel implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agendamento_tarefa")
    private Long id;
    @Column(nullable = false, length = 100)
    private String titulo;
    private String descricao;
    @Column(name = "dataVacimento", nullable = false)
    private LocalDateTime dataVencimento;
    @Column(nullable = false)
    private LocalDateTime dataCadastro;
    @Column(nullable = false)
    private Boolean tarefaConcluida;
    @ManyToOne
    @JoinColumn(name = "id_status", referencedColumnName = "id_status")
    private Status idStatus;
    @ManyToMany
    @JoinTable(name = "usuario_agendamento_tarefa"),
        joinColumns = @JoinColumn(name = "id_agendamento_tarefa"),
        inverseJoinColumns = @JoinColumn(name = "id_usuario")
    private List<Usuario> usuarios;
}
