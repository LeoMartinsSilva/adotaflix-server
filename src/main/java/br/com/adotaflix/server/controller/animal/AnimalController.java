package br.com.adotaflix.server.controller.animal;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.adotaflix.server.dto.filter.animal.AnimalFilterDto;
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
	
	@GetMapping("/catalogo")
	public ResponseEntity<List<AnimalDto>> buscarCatalogo(@RequestParam("filters") String filtersJson) throws JsonMappingException, JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		AnimalFilterDto filters = new AnimalFilterDto();
		if(filtersJson!=null && !filtersJson.isBlank()) {
			filters = mapper.readValue(filtersJson, AnimalFilterDto.class);
		}
		
		return ResponseEntity.ok(animalService.buscarCatalogo(filters));
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
	
	@PostMapping(value = "/imagens/{idAnimal}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Void> inserirImagens(@PathVariable Long idAnimal, @RequestParam(value = "images", required = false) MultipartFile... files) {
		animalService.inserirImagens(idAnimal,  files);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value = "/imagens/{idAnimal}")
	public ResponseEntity<List<String>> buscarImagens(@PathVariable Long idAnimal){
		return ResponseEntity.ok(animalService.buscarImagens(idAnimal));	
	}
	
	
}
