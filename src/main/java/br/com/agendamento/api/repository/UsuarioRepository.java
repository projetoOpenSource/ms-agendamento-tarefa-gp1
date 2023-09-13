package br.com.agendamento.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.agendamento.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	boolean existsByEmail(String email);

	Usuario findByEmail(String email);

}
