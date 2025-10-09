package br.com.adotaflix.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.adotaflix.server.config.TokenConfig;
import br.com.adotaflix.server.dto.request.loginRequest;
import br.com.adotaflix.server.dto.response.LoginResponse;
import br.com.adotaflix.server.model.Usuario;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final TokenConfig tokenConfig;
	
	public AuthController(AuthenticationManager authenticationManager,
			TokenConfig tokenConfig) {
		this.authenticationManager = authenticationManager;
		this.tokenConfig = tokenConfig;
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody loginRequest login){
		
		UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(login.email(), login.password());
		Authentication auth = authenticationManager.authenticate(userAndPass);
		
		Usuario user = (Usuario) auth.getPrincipal();
		String token = tokenConfig.generateToken(user);
		return ResponseEntity.ok(new LoginResponse(token));
		
	}
	
	
}
