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

import br.com.adotaflix.server.dto.request.animal.animal.AlterarAnimalRequestDto;
import br.com.adotaflix.server.dto.request.animal.animal.RegistrarAnimalRequestDto;
import br.com.adotaflix.server.dto.response.animal.AnimalDto;
import br.com.adotaflix.server.service.animal.AnimalService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/animal")
public class AnimalController {
	
	private final AnimalService animalService;
	
	public AnimalController(AnimalService animalService) {
		this.animalService = animalService;
	}
	
	@GetMapping
	public ResponseEntity<List<AnimalDto>> buscar(){
		return ResponseEntity.ok(animalService.buscar());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AnimalDto> buscarPorId(@PathVariable Long id){
		return ResponseEntity.ok(animalService.buscarPorId(id));
	}
	
	@PostMapping
	public ResponseEntity<AnimalDto> registrar(@RequestBody @Valid RegistrarAnimalRequestDto dados){
		return ResponseEntity.status(HttpStatus.CREATED).body(animalService.registrar(dados));
	}
	
	@PutMapping
	public ResponseEntity<AnimalDto> alterar(@RequestBody @Valid AlterarAnimalRequestDto dados){
		return ResponseEntity.ok(animalService.alterar(dados));
	}
	
}
