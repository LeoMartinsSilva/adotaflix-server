package br.com.adotaflix.server.mapper.animal;

import org.springframework.stereotype.Component;

import br.com.adotaflix.server.dto.response.animal.RacaDto;
import br.com.adotaflix.server.mapper.AbstractMapper;
import br.com.adotaflix.server.model.animal.Raca;

@Component
public class RacaMapper extends AbstractMapper<RacaDto, Raca> {

	private final EspecieMapper especieMapper;
	
	public RacaMapper(EspecieMapper especieMapper) {
		this.especieMapper = especieMapper;
	}
	
	@Override
	public RacaDto toDto(Raca entity) {
		if(entity == null) return null;
		return RacaDto.builder()
				.id(entity.getId())
				.nome(entity.getNome())
				.especie(especieMapper.toDto(entity.getEspecie()))
				.build();
	}

	@Override
	public Raca toEntity(RacaDto dto) {
		if(dto == null) return null;
		return Raca.builder()
				.id(dto.getId())
				.nome(dto.getNome())
				.especie(especieMapper.toEntity(dto.getEspecie()))
				.build();
	}

}
