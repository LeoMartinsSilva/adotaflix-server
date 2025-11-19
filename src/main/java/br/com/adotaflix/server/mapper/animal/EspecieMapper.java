package br.com.adotaflix.server.mapper.animal;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.adotaflix.server.dto.response.animal.EspecieDto;
import br.com.adotaflix.server.dto.response.animal.RacaDto;
import br.com.adotaflix.server.mapper.AbstractMapper;
import br.com.adotaflix.server.model.animal.Especie;
import br.com.adotaflix.server.model.animal.Raca;

@Component
public class EspecieMapper extends AbstractMapper<EspecieDto, Especie> {
	
	@Override
	public EspecieDto toDto(Especie entity) {
		if(entity == null) return null;
		
		return EspecieDto.builder()
				.id(entity.getId())
				.nome(entity.getNome())
				.racas(
		                entity.getRacas() == null ? null :
		                    entity.getRacas().stream()
		                    	.map((Raca r) -> new RacaDto(r.getId(), r.getNome(), null))
		                        .collect(Collectors.toList())
		            )
				.build();
	}

	@Override
	public Especie toEntity(EspecieDto dto) {
		if(dto == null) return null;
		
		return Especie.builder()
				.id(dto.getId())
				.nome(dto.getNome())
				.racas(
		                dto.getRacas() == null ? null :
		                    dto.getRacas().stream()
		                    	.map((RacaDto r) -> new Raca(r.getId(), r.getNome(), null))
		                        .collect(Collectors.toList())
		            )
				.build();
	}

}
