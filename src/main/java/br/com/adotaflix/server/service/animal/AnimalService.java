package br.com.adotaflix.server.service.animal;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.adotaflix.server.config.exception.NotFoundException;
import br.com.adotaflix.server.dto.request.animal.animal.AlterarAnimalRequestDto;
import br.com.adotaflix.server.dto.request.animal.animal.RegistrarAnimalRequestDto;
import br.com.adotaflix.server.dto.response.animal.AnimalDto;
import br.com.adotaflix.server.mapper.animal.AnimalMapper;
import br.com.adotaflix.server.model.Usuario;
import br.com.adotaflix.server.model.animal.Animal;
import br.com.adotaflix.server.model.animal.Cor;
import br.com.adotaflix.server.model.animal.Raca;
import br.com.adotaflix.server.repository.animal.AnimalRepository;
import br.com.adotaflix.server.repository.animal.CorRepository;
import br.com.adotaflix.server.repository.animal.RacaRepository;
import br.com.adotaflix.server.service.UsuarioLogadoService;
import jakarta.transaction.Transactional;

@Service
public class AnimalService {
	private final AnimalRepository animalRepository;
	private final AnimalMapper animalMapper;
	private final UsuarioLogadoService usuarioLogadoService;
	private final RacaRepository racaRepository;
	private final CorRepository corRepository;
	
	public AnimalService(
			AnimalRepository animalRepository, 
			AnimalMapper animalMapper, 
			UsuarioLogadoService usuarioLogadoService,
			RacaRepository racaRepository,
			CorRepository corRepository) {
		this.animalRepository = animalRepository;
		this.animalMapper = animalMapper;
		this.usuarioLogadoService = usuarioLogadoService;
		this.racaRepository = racaRepository;
		this.corRepository = corRepository;
	}
	
	public List<AnimalDto> buscar(){
		return animalMapper.toDto(animalRepository.findAll());
	}
	
	public AnimalDto buscarPorId(Long id){
		return animalMapper.toDto(animalRepository.findById(id).orElseThrow(()->new NotFoundException("Animal não encontrado")));
	}
	
	
	@Transactional
	public AnimalDto registrar(RegistrarAnimalRequestDto dados) {
		Usuario usuario = usuarioLogadoService.getUsuarioLogado();
		if(usuario.getInstituicao()==null) {
			throw new RuntimeException("Você não está registrado em nenhuma instituição");
		}
		Raca raca = racaRepository.findById(dados.getIdRaca()).orElseThrow(()-> new NotFoundException("Raça não encontrada"));
		Cor cor = corRepository.findById(dados.getIdCor()).orElseThrow(()-> new NotFoundException("Cor não encontrada"));
		
		Animal animal = Animal.builder()
				.nome(dados.getNome())
				.descricao(dados.getDescricao())
				.dataNascimento(dados.getDataNascimento())
				.raca(raca)
				.cor(cor)
				.instituicao(usuario.getInstituicao())
				.porte(dados.getPorte())
				.status("D")
				.sexo(dados.getSexo())
				.build();
		
		animal = animalRepository.save(animal);
		return animalMapper.toDto(animal);
	}
	
	@Transactional
	public AnimalDto alterar(AlterarAnimalRequestDto dados) {
		Animal animal = animalRepository.findById(dados.getId()).orElseThrow(()->new NotFoundException("Animal não encontrado"));
		
		Usuario usuario = usuarioLogadoService.getUsuarioLogado();
		if(usuario.getInstituicao()==null) {
			throw new RuntimeException("Você não está registrado em nenhuma instituição");
		}
		if(!usuario.getInstituicao().getId().equals(animal.getInstituicao().getId())) {
			throw new RuntimeException("Esse animal não pertence a sua instituição");	
		}
		
		Raca raca = racaRepository.findById(dados.getIdRaca()).orElseThrow(()-> new NotFoundException("Raça não encontrada"));
		Cor cor = corRepository.findById(dados.getIdCor()).orElseThrow(()-> new NotFoundException("Cor não encontrada"));
		
		
		animal.setNome(dados.getNome());
		animal.setDescricao(dados.getDescricao());
		animal.setRaca(raca);
		animal.setCor(cor);
		animal.setDataNascimento(dados.getDataNascimento());
		animal.setPorte(dados.getPorte());
		animal.setSexo(dados.getSexo());
		return animalMapper.toDto(animal);
	}

	@Transactional
	public void marcarComoAdotado(Long id) {
		Animal animal = animalRepository.findById(id).orElseThrow(()->new NotFoundException("Animal não encontrado"));
		animal.setStatus("A");
	}
}
