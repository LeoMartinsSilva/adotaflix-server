package br.com.adotaflix.server.service.animal;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.adotaflix.server.config.exception.NotFoundException;
import br.com.adotaflix.server.dto.request.animal.especie.AlterarEspecieRequestDto;
import br.com.adotaflix.server.dto.request.animal.especie.RegistrarEspecieRequestDto;
import br.com.adotaflix.server.dto.response.animal.EspecieDto;
import br.com.adotaflix.server.mapper.animal.EspecieMapper;
import br.com.adotaflix.server.model.animal.Especie;
import br.com.adotaflix.server.repository.animal.EspecieRepository;
import jakarta.transaction.Transactional;

@Service
public class EspecieService {
	private final EspecieRepository especieRepository;
	private final EspecieMapper especieMapper;
	
	public EspecieService(EspecieRepository especieRepository, EspecieMapper especieMapper) {
		this.especieRepository = especieRepository;
		this.especieMapper = especieMapper;
	}
	
	public List<EspecieDto> buscar(){
		return especieMapper.toDto(especieRepository.findAll());
	}
	
	public EspecieDto buscarPorId(Long id){
		return especieMapper.toDto(especieRepository.findById(id).orElseThrow(()->new NotFoundException("Espécie não encontrada")));
	}
	
	
	@Transactional
	public EspecieDto registrar(RegistrarEspecieRequestDto dados) {
		Especie especie = Especie.builder()
				.nome(dados.getNome())
				.build();
		
		especie = especieRepository.save(especie);
		return especieMapper.toDto(especie);
	}
	
	@Transactional
	public EspecieDto alterar(AlterarEspecieRequestDto dados) {
		Especie especie = especieRepository.findById(dados.getId()).orElseThrow(()->new NotFoundException("Espécie não encontrada"));
		especie.setNome(dados.getNome());
		return especieMapper.toDto(especie);
	}
}
