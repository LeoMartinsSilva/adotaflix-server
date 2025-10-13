package br.com.adotaflix.server.mapper.animal;

import org.springframework.stereotype.Component;

import br.com.adotaflix.server.dto.response.animal.AnimalDto;
import br.com.adotaflix.server.mapper.AbstractMapper;
import br.com.adotaflix.server.mapper.InstituicaoMapper;
import br.com.adotaflix.server.model.animal.Animal;

@Component
public class AnimalMapper extends AbstractMapper<AnimalDto, Animal> {

	private final RacaMapper racaMapper;
	private final CorMapper corMapper;
	private final InstituicaoMapper instituicaoMapper;
	
	public AnimalMapper(RacaMapper racaMapper, CorMapper corMapper, InstituicaoMapper instituicaoMapper) {
		this.racaMapper = racaMapper;
		this.corMapper = corMapper;
		this.instituicaoMapper = instituicaoMapper;
	}
	
	@Override
	public AnimalDto toDto(Animal entity) {
		if(entity == null) return null;
		return AnimalDto.builder()
				.id(entity.getId())
				.nome(entity.getNome())
				.descricao(entity.getDescricao())
				.dataNascimento(entity.getDataNascimento())
				.cor(corMapper.toDto(entity.getCor()))
				.raca(racaMapper.toDto(entity.getRaca()))
				.instituicao(instituicaoMapper.toDto(entity.getInstituicao()))
				.porte(entity.getPorte())
				.status(entity.getStatus())
				.sexo(entity.getSexo())
				.build();
	}

	@Override
	public Animal toEntity(AnimalDto dto) {
		if(dto == null) return null;
		return Animal.builder()
				.id(dto.getId())
				.nome(dto.getNome())
				.descricao(dto.getDescricao())
				.dataNascimento(dto.getDataNascimento())
				.cor(corMapper.toEntity(dto.getCor()))
				.raca(racaMapper.toEntity(dto.getRaca()))
				.instituicao(instituicaoMapper.toEntity(dto.getInstituicao()))
				.porte(dto.getPorte())
				.status(dto.getStatus())
				.sexo(dto.getSexo())
				.build();
	}

}
