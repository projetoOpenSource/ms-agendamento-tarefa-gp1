package br.com.agendamento.api.repository;

import br.com.agendamento.api.model.CreateTaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author leovizeu
 */
@Repository
public interface CreateTaskRepository extends JpaRepository<CreateTaskModel, Long> {
}
