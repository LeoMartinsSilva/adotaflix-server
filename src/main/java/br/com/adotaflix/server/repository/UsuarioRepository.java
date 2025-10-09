package br.com.adotaflix.server.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.adotaflix.server.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<UserDetails> findUserByEmail(String email);
}
