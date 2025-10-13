package br.com.adotaflix.server.service.animal;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.adotaflix.server.config.exception.NotFoundException;
import br.com.adotaflix.server.dto.request.animal.raca.AlterarRacaRequestDto;
import br.com.adotaflix.server.dto.request.animal.raca.RegistrarRacaRequestDto;
import br.com.adotaflix.server.dto.response.animal.RacaDto;
import br.com.adotaflix.server.mapper.animal.RacaMapper;
import br.com.adotaflix.server.model.animal.Especie;
import br.com.adotaflix.server.model.animal.Raca;
import br.com.adotaflix.server.repository.animal.EspecieRepository;
import br.com.adotaflix.server.repository.animal.RacaRepository;
import jakarta.transaction.Transactional;

@Service
public class RacaService {
	private final RacaRepository racaRepository;
	private final RacaMapper racaMapper;
	private final EspecieRepository especieRepository;
	
	public RacaService(RacaRepository racaRepository, RacaMapper racaMapper, EspecieRepository especieRepository) {
		this.racaRepository = racaRepository;
		this.racaMapper = racaMapper;
		this.especieRepository = especieRepository;
	}
	
	public List<RacaDto> buscar(){
		return racaMapper.toDto(racaRepository.findAll());
	}
	
	public RacaDto buscarPorId(Long id){
		return racaMapper.toDto(racaRepository.findById(id).orElseThrow(()->new NotFoundException("Raça não encontrada")));
	}
	
	
	@Transactional
	public RacaDto registrar(RegistrarRacaRequestDto dados) {
		
		Especie especie = especieRepository.findById(dados.getIdEspecie()).orElseThrow(()-> new NotFoundException("Espécie não encontrada"));
		
		Raca raca = Raca.builder()
				.nome(dados.getNome())
				.especie(especie)
				.build();
		
		raca = racaRepository.save(raca);
		return racaMapper.toDto(raca);
	}
	
	@Transactional
	public RacaDto alterar(AlterarRacaRequestDto dados) {
		Raca raca = racaRepository.findById(dados.getId()).orElseThrow(()->new NotFoundException("Raça não encontrada"));
		Especie especie = especieRepository.findById(dados.getIdEspecie()).orElseThrow(()-> new NotFoundException("Espécie não encontrada"));
		
		
		raca.setNome(dados.getNome());
		raca.setEspecie(especie);
		return racaMapper.toDto(raca);
	}
}
