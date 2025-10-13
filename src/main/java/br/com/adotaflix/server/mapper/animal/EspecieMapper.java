package br.com.adotaflix.server.mapper.animal;

import org.springframework.stereotype.Component;

import br.com.adotaflix.server.dto.response.animal.EspecieDto;
import br.com.adotaflix.server.mapper.AbstractMapper;
import br.com.adotaflix.server.model.animal.Especie;

@Component
public class EspecieMapper extends AbstractMapper<EspecieDto, Especie> {

	@Override
	public EspecieDto toDto(Especie entity) {
		if(entity == null) return null;
		
		return EspecieDto.builder()
				.id(entity.getId())
				.nome(entity.getNome())
				.build();
	}

	@Override
	public Especie toEntity(EspecieDto dto) {
		if(dto == null) return null;
		
		return Especie.builder()
				.id(dto.getId())
				.nome(dto.getNome())
				.build();
	}

}
