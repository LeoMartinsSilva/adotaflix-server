package br.com.adotaflix.server.controller.animal;

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

import br.com.adotaflix.server.dto.request.animal.cor.AlterarCorRequestDto;
import br.com.adotaflix.server.dto.request.animal.cor.RegistrarCorRequestDto;
import br.com.adotaflix.server.dto.response.animal.CorDto;
import br.com.adotaflix.server.service.animal.CorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cor")
public class CorController {
	
	private final CorService corService;
	
	public CorController(CorService corService) {
		this.corService = corService;
	}
	
	@GetMapping
	public ResponseEntity<List<CorDto>> buscar(){
		return ResponseEntity.ok(corService.buscar());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CorDto> buscarPorId(@PathVariable Long id){
		return ResponseEntity.ok(corService.buscarPorId(id));
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CorDto> registrar(@RequestBody @Valid RegistrarCorRequestDto dados){
		return ResponseEntity.status(HttpStatus.CREATED).body(corService.registrar(dados));
	}
	
	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CorDto> alterar(@RequestBody @Valid AlterarCorRequestDto dados){
		return ResponseEntity.ok(corService.alterar(dados));
	}
}
