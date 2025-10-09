package br.com.adotaflix.server.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.adotaflix.server.repository.UsuarioRepository;

@Service
public class AuthConfig implements UserDetailsService {

	private final UsuarioRepository userRepository;
	
	public AuthConfig(UsuarioRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
	}

}
