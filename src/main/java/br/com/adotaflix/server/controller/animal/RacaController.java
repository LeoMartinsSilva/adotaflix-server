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

import br.com.adotaflix.server.dto.request.animal.raca.AlterarRacaRequestDto;
import br.com.adotaflix.server.dto.request.animal.raca.RegistrarRacaRequestDto;
import br.com.adotaflix.server.dto.response.animal.RacaDto;
import br.com.adotaflix.server.service.animal.RacaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/raca")
public class RacaController {
	
	private final RacaService racaService;
	
	public RacaController(RacaService racaService) {
		this.racaService = racaService;
	}
	
	@GetMapping
	public ResponseEntity<List<RacaDto>> buscar(){
		return ResponseEntity.ok(racaService.buscar());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RacaDto> buscarPorId(@PathVariable Long id){
		return ResponseEntity.ok(racaService.buscarPorId(id));
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<RacaDto> registrar(@RequestBody @Valid RegistrarRacaRequestDto dados){
		return ResponseEntity.status(HttpStatus.CREATED).body(racaService.registrar(dados));
	}
	
	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<RacaDto> alterar(@RequestBody @Valid AlterarRacaRequestDto dados){
		return ResponseEntity.ok(racaService.alterar(dados));
	}
}
