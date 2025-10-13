package br.com.adotaflix.server.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.adotaflix.server.dto.request.AlterarUsuarioLogadoRequestDto;
import br.com.adotaflix.server.dto.request.RegistrarUsuarioRequestDto;
import br.com.adotaflix.server.dto.response.RegistrarUsuarioResponseDto;
import br.com.adotaflix.server.dto.response.UsuarioDto;
import br.com.adotaflix.server.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	private final UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<UsuarioDto>> buscar(){
		return ResponseEntity.ok(usuarioService.buscar());
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UsuarioDto> buscarPorId(@PathVariable Long id){
		return ResponseEntity.ok(usuarioService.buscarPorId(id));
	}
	
	@PostMapping("/public")
	public ResponseEntity<RegistrarUsuarioResponseDto> registrarPublic(@RequestBody @Valid RegistrarUsuarioRequestDto dados){
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.registrar(dados));
	}
	
	@PutMapping("/tornarAdmin/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> tornarAdmin(@PathVariable Long id){
		usuarioService.tornarAdmin(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/usuarioLogado")
	public ResponseEntity<UsuarioDto> AlterarUsuarioLogado(@RequestBody AlterarUsuarioLogadoRequestDto dados){
		return ResponseEntity.ok(usuarioService.alterarUsuarioLogado(dados));
	}
}
