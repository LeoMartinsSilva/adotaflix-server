package br.com.adotaflix.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.adotaflix.server.dto.request.RegistrarUsuarioRequestDto;
import br.com.adotaflix.server.dto.response.RegistrarUsuarioResponseDto;
import br.com.adotaflix.server.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	private final UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@PostMapping("/public")
	public ResponseEntity<RegistrarUsuarioResponseDto> registrarPublic(@RequestBody @Valid RegistrarUsuarioRequestDto dados){
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.registrar(dados));
	}
}
