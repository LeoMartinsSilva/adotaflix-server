package br.com.adotaflix.server.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.adotaflix.server.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<UserDetails> findUserByEmail(String email);

	@Query("select EXTRACT(MONTH FROM dataCadastro) mes, count(u) total from Usuario u group by EXTRACT(MONTH FROM dataCadastro)")
	List<Map<String, Object>> usuariosPorMes();
}
