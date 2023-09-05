package br.com.agendamento.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.agendamento.api.model.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {

	Optional<Status> findByTipoStatusAndStatus(String tipoStatus, String status);

}
