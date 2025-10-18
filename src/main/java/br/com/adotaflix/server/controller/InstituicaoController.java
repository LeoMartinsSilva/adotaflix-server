package br.com.adotaflix.server.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.adotaflix.server.dto.request.AlterarInstituicaoRequestDto;
import br.com.adotaflix.server.dto.request.RegistrarInstituicaoRequestDto;
import br.com.adotaflix.server.dto.response.InstituicaoDto;
import br.com.adotaflix.server.dto.response.RegistrarInstituicaoResponseDto;
import br.com.adotaflix.server.dto.response.dashboard.InstituicaoDashboardDto;
import br.com.adotaflix.server.service.InstituicaoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/instituicao")
public class InstituicaoController {
	
	private final InstituicaoService instituicaoService;
	
	public InstituicaoController(InstituicaoService instituicaoService) {
		this.instituicaoService = instituicaoService;
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<InstituicaoDto>> buscar(){
		return ResponseEntity.ok(instituicaoService.buscar());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<InstituicaoDto> buscarPorId(@PathVariable Long id){
		return ResponseEntity.ok(instituicaoService.buscarPorId(id));
	}
	
	@GetMapping("/dashboard")
	public ResponseEntity<InstituicaoDashboardDto> buscarDashboard(){
		return ResponseEntity.ok(instituicaoService.buscarDadosDashboard());
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<RegistrarInstituicaoResponseDto> registrar(@RequestBody @Valid RegistrarInstituicaoRequestDto dados){
		return ResponseEntity.status(HttpStatus.CREATED).body(instituicaoService.registrar(dados));
	}
	
	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<InstituicaoDto> alterar(@RequestBody @Valid AlterarInstituicaoRequestDto dados){
		return ResponseEntity.ok(instituicaoService.alterar(dados));
	}
	
	@PostMapping("/adicionarUsuario/{idInstituicao}/{idUsuario}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> adicionarUsuario(@PathVariable Long idInstituicao, @PathVariable Long idUsuario){
		instituicaoService.adicionarUsuario(idInstituicao, idUsuario);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/adicionarUsuario/{idUsuario}")
	public ResponseEntity<Void> adicionarUsuario(@PathVariable Long idUsuario){
		instituicaoService.adicionarUsuario(idUsuario);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping(value = "/imagem/{idInstituicao}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Void> inserirImagens(@PathVariable Long idInstituicao, @RequestParam(value = "image", required = false) MultipartFile file) {
		instituicaoService.inserirImagem(idInstituicao, file);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value = "/imagem/{idInstituicao}")
	public ResponseEntity<String> buscarImagem(@PathVariable Long idInstituicao){
		return ResponseEntity.ok(instituicaoService.buscarImagem(idInstituicao));	
	}
}
