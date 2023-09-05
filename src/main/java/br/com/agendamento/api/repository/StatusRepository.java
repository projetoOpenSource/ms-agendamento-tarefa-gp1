package br.com.agendamento.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.agendamento.api.model.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {

}
