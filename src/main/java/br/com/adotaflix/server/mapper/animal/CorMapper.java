package br.com.adotaflix.server.mapper.animal;

import org.springframework.stereotype.Component;

import br.com.adotaflix.server.dto.response.animal.CorDto;
import br.com.adotaflix.server.mapper.AbstractMapper;
import br.com.adotaflix.server.model.animal.Cor;

@Component
public class CorMapper extends AbstractMapper<CorDto, Cor> {

	@Override
	public CorDto toDto(Cor entity) {
		if (entity == null) return null;
		return CorDto.builder()
				.id(entity.getId())
				.nome(entity.getNome())
				.build();
	}

	@Override
	public Cor toEntity(CorDto dto) {
		if (dto == null) return null;
		return Cor.builder()
				.id(dto.getId())
				.nome(dto.getNome())
				.build();
	}

}
