package br.com.adotaflix.server.service;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.adotaflix.server.config.JWTUserData;
import br.com.adotaflix.server.model.Usuario;
import br.com.adotaflix.server.repository.UsuarioRepository;

@Service
public class UsuarioLogadoService {

	private final UsuarioRepository usuarioRepository;
	
	public UsuarioLogadoService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public Usuario getUsuarioLogado() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {

			JWTUserData userData = (JWTUserData) authentication.getPrincipal();
			Optional<Usuario> optional = usuarioRepository.findById(userData.userId());
			if(optional.isPresent()) {
				return optional.get();
			}
		}

		return null;
	}
	

}
