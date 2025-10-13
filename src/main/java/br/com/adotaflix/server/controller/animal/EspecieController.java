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

import br.com.adotaflix.server.dto.request.animal.especie.AlterarEspecieRequestDto;
import br.com.adotaflix.server.dto.request.animal.especie.RegistrarEspecieRequestDto;
import br.com.adotaflix.server.dto.response.animal.EspecieDto;
import br.com.adotaflix.server.service.animal.EspecieService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/especie")
public class EspecieController {
	
	private final EspecieService especieService;
	
	public EspecieController(EspecieService especieService) {
		this.especieService = especieService;
	}
	
	@GetMapping
	public ResponseEntity<List<EspecieDto>> buscar(){
		return ResponseEntity.ok(especieService.buscar());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EspecieDto> buscarPorId(@PathVariable Long id){
		return ResponseEntity.ok(especieService.buscarPorId(id));
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<EspecieDto> registrar(@RequestBody @Valid RegistrarEspecieRequestDto dados){
		return ResponseEntity.status(HttpStatus.CREATED).body(especieService.registrar(dados));
	}
	
	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<EspecieDto> alterar(@RequestBody @Valid AlterarEspecieRequestDto dados){
		return ResponseEntity.ok(especieService.alterar(dados));
	}
}
