package br.com.adotaflix.server.config;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.adotaflix.server.model.Usuario;
import br.com.adotaflix.server.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{
	
	private final TokenConfig tokenConfig;
	private final UsuarioRepository usuarioRepository;
	
	public SecurityFilter(TokenConfig tokenConfig, UsuarioRepository usuarioRepository) {
		this.tokenConfig = tokenConfig;
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorizationHeader = request.getHeader("Authorization");
		if(authorizationHeader !=null && !Strings.isEmpty(authorizationHeader) && authorizationHeader.startsWith("Bearer")) {
			String token = authorizationHeader.substring("Bearer ".length());
			Optional<JWTUserData> optional = tokenConfig.validadeToken(token);
			if(optional.isPresent()) {
				JWTUserData userData = optional.get();
				Usuario usuario = usuarioRepository.findById(userData.userId()).orElse(null);
				UsernamePasswordAuthenticationToken token1 = new UsernamePasswordAuthenticationToken(userData, userData.email(), List.of(new SimpleGrantedAuthority(usuario.getRole())));
				SecurityContextHolder.getContext().setAuthentication(token1);
			}
		}

		filterChain.doFilter(request, response);
	}
}
