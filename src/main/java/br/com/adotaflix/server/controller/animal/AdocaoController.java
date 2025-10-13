package br.com.adotaflix.server.controller.animal;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.adotaflix.server.dto.request.ResponderSolicitacaoAdocaoRequestDto;
import br.com.adotaflix.server.dto.request.SolicitarAdocaoRequestDto;
import br.com.adotaflix.server.dto.response.animal.SolicitacaoAdocaoDto;
import br.com.adotaflix.server.service.animal.SolicitacaoAdocaoService;

@RestController
@RequestMapping("/adocao")
public class AdocaoController {

	private final SolicitacaoAdocaoService solicitacaoAdocaoService;
	
	public AdocaoController(SolicitacaoAdocaoService solicitacaoAdocaoService) {
		this.solicitacaoAdocaoService = solicitacaoAdocaoService;
	}
	
	@GetMapping("/solicitacoesMinhaInstituicao")
	public ResponseEntity<List<SolicitacaoAdocaoDto>> buscarSolicitacoesMinhaInstituicao(){
		return ResponseEntity.ok(solicitacaoAdocaoService.buscarSolicitacoesMinhaInstituicao());
	}
	
	@GetMapping("/minhasSolicitacoes")
	public ResponseEntity<List<SolicitacaoAdocaoDto>> buscarMinhasSolicitacoes(){
		return ResponseEntity.ok(solicitacaoAdocaoService.buscarMinhasSolicitacoes());
	}
	
	@PostMapping("/solicitar")
	public ResponseEntity<SolicitacaoAdocaoDto> solicitarAdocao(@RequestBody SolicitarAdocaoRequestDto dados){
		return ResponseEntity.status(HttpStatus.CREATED).body(solicitacaoAdocaoService.solicitar(dados));
	}
	
	@PostMapping("/responder")
	public ResponseEntity<SolicitacaoAdocaoDto> responderSolicitacaoAdocao(@RequestBody ResponderSolicitacaoAdocaoRequestDto dados){
		return ResponseEntity.ok(solicitacaoAdocaoService.responserSolicitacao(dados));
	}
}
