package br.com.agendamento.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.agendamento.api.model.Usuario;
import br.com.agendamento.api.model.UsuarioToken;

public interface UsuarioTokenRepository extends JpaRepository<UsuarioToken, Long> {

	UsuarioToken findByUsuario(Usuario usuario);

}
