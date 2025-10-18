package br.com.adotaflix.server.service.animal;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.adotaflix.server.config.exception.NotFoundException;
import br.com.adotaflix.server.dto.request.ResponderSolicitacaoAdocaoRequestDto;
import br.com.adotaflix.server.dto.request.SolicitarAdocaoRequestDto;
import br.com.adotaflix.server.dto.response.animal.SolicitacaoAdocaoDto;
import br.com.adotaflix.server.mapper.animal.SolicitacaoAdocaoMapper;
import br.com.adotaflix.server.model.Usuario;
import br.com.adotaflix.server.model.animal.Animal;
import br.com.adotaflix.server.model.animal.SolicitacaoAdocao;
import br.com.adotaflix.server.repository.animal.AnimalRepository;
import br.com.adotaflix.server.repository.animal.SolicitacaoAdocaoRepository;
import br.com.adotaflix.server.service.UsuarioLogadoService;
import jakarta.transaction.Transactional;

@Service
public class SolicitacaoAdocaoService {
	private final SolicitacaoAdocaoRepository solicitacaoAdocaoRepository;
	private final SolicitacaoAdocaoMapper solicitacaoAdocaoMapper;
	private final UsuarioLogadoService usuarioLogadoService;
	private final AnimalRepository animalRepository;
	private final AnimalService animalService;
	
	public SolicitacaoAdocaoService(
			SolicitacaoAdocaoRepository solicitacaoAdocaoRepository, 
			SolicitacaoAdocaoMapper solicitacaoAdocaoMapper, 
			UsuarioLogadoService usuarioLogadoService,
			AnimalRepository animalRepository,
			AnimalService animalService) {
		this.solicitacaoAdocaoRepository = solicitacaoAdocaoRepository;
		this.solicitacaoAdocaoMapper = solicitacaoAdocaoMapper;
		this.usuarioLogadoService = usuarioLogadoService;
		this.animalRepository = animalRepository;
		this.animalService = animalService;
	}
	
	public List<SolicitacaoAdocaoDto> buscar(){
		return solicitacaoAdocaoMapper.toDto(solicitacaoAdocaoRepository.findAll());
	}
	
	public List<SolicitacaoAdocaoDto> buscarMinhasSolicitacoes(){
		Usuario usuario = usuarioLogadoService.getUsuarioLogado();
		return solicitacaoAdocaoMapper.toDto(solicitacaoAdocaoRepository.findAllByUsuarioId(usuario.getId()));
	}
	
	public List<SolicitacaoAdocaoDto> buscarSolicitacoesMinhaInstituicao() {
		Usuario usuario = usuarioLogadoService.getUsuarioLogado();
		if(usuario.getInstituicao()==null) {
			throw new RuntimeException("Você não está registrado em nenhuma instituição");
		}
		return solicitacaoAdocaoMapper.toDto(solicitacaoAdocaoRepository.findAllByAnimalInstituicaoId(usuario.getInstituicao().getId()));
	}
	
	public SolicitacaoAdocaoDto buscarPorId(Long id){
		return solicitacaoAdocaoMapper.toDto(solicitacaoAdocaoRepository.findById(id).orElseThrow(()->new NotFoundException("SolicitacaoAdocao não encontrada")));
	}
	
	@Transactional
	public SolicitacaoAdocaoDto solicitar(SolicitarAdocaoRequestDto dados) {
		Usuario usuario = usuarioLogadoService.getUsuarioLogado();
		
		Animal animal = animalRepository.findById(dados.getIdAnimal()).orElseThrow(()-> new NotFoundException("Animal não encontrado"));
		if(!animal.getStatus().equals("D")) {
			throw new RuntimeException("Esse animal não está disponível para adoção");
		}
		SolicitacaoAdocao solicitacaoAdocao = SolicitacaoAdocao.builder()
				.usuario(usuario)
				.animal(animal)
				.dataSolicitacao(LocalDateTime.now())
				.build();
		
		solicitacaoAdocao = solicitacaoAdocaoRepository.save(solicitacaoAdocao);
		return solicitacaoAdocaoMapper.toDto(solicitacaoAdocao);
	}
	
	@Transactional
	public SolicitacaoAdocaoDto responserSolicitacao(ResponderSolicitacaoAdocaoRequestDto dados) {
		Usuario usuario = usuarioLogadoService.getUsuarioLogado();
		SolicitacaoAdocao solicitacao = solicitacaoAdocaoRepository.findById(dados.getIdSolicitacao()).orElseThrow(()-> new NotFoundException("Solicitação não encontrada"));
		if(usuario.getInstituicao()==null || !solicitacao.getAnimal().getInstituicao().getId().equals(usuario.getInstituicao().getId())) {
			throw new RuntimeException("Esse animal não pertence a sua instituição");
		}
		if(solicitacao.getResposta()!=null) {
			throw new RuntimeException("Esse solicitação já foi respondida");	
		}
		if(dados.getResposta().equals("A") && !solicitacao.getAnimal().getStatus().equals("D")) {
			throw new RuntimeException("Não é possível aprovar essa solicitação pois esse animal não está mais disponível");
		}
		
		solicitacao.setResposta(dados.getResposta());
		solicitacao.setDescricaoResposta(dados.getDescricaoResposta());
		solicitacao.setDataResposta(LocalDateTime.now());
		
		if(dados.getResposta().equals("A")) {
			animalService.marcarComoAdotado(solicitacao.getAnimal().getId());
		}
		
		return solicitacaoAdocaoMapper.toDto(solicitacao);
		
	}
	
}
