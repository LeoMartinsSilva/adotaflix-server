package br.com.adotaflix.server.config;

import java.io.IOException;
import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{
	
	private final TokenConfig tokenConfig;
	
	public SecurityFilter(TokenConfig tokenConfig) {
		this.tokenConfig = tokenConfig;
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
				UsernamePasswordAuthenticationToken token1 = new UsernamePasswordAuthenticationToken(userData.email(), userData.email());
				SecurityContextHolder.getContext().setAuthentication(token1);
			}
			filterChain.doFilter(request, response);
		}else {
			filterChain.doFilter(request, response);
		}
	}
}
