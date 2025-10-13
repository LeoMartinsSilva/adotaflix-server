package br.com.adotaflix.server.mapper.animal;

import org.springframework.stereotype.Component;

import br.com.adotaflix.server.dto.response.animal.SolicitacaoAdocaoDto;
import br.com.adotaflix.server.mapper.AbstractMapper;
import br.com.adotaflix.server.mapper.UsuarioMapper;
import br.com.adotaflix.server.model.animal.SolicitacaoAdocao;

@Component
public class SolicitacaoAdocaoMapper extends AbstractMapper<SolicitacaoAdocaoDto, SolicitacaoAdocao> {

	private final AnimalMapper animalMapper;
	private final UsuarioMapper usuarioMapper;
	
	public SolicitacaoAdocaoMapper(AnimalMapper animalMapper, UsuarioMapper usuarioMapper) {
		this.animalMapper = animalMapper;
		this.usuarioMapper = usuarioMapper;
	}
	
	@Override
	public SolicitacaoAdocaoDto toDto(SolicitacaoAdocao entity) {
		if(entity == null) return null;
		return SolicitacaoAdocaoDto.builder()
				.id(entity.getId())
				.usuario(usuarioMapper.toDto(entity.getUsuario()))
				.animal(animalMapper.toDto(entity.getAnimal()))
				.dataSolicitacao(entity.getDataSolicitacao())
				.resposta(entity.getResposta())
				.descricaoResposta(entity.getDescricaoResposta())
				.dataResposta(entity.getDataResposta())
				.build();
	}

	@Override
	public SolicitacaoAdocao toEntity(SolicitacaoAdocaoDto dto) {
		if(dto == null) return null;
		return SolicitacaoAdocao.builder()
				.id(dto.getId())
				.usuario(usuarioMapper.toEntity(dto.getUsuario()))
				.animal(animalMapper.toEntity(dto.getAnimal()))
				.dataSolicitacao(dto.getDataSolicitacao())
				.resposta(dto.getResposta())
				.descricaoResposta(dto.getDescricaoResposta())
				.dataResposta(dto.getDataResposta())
				.build();
	}

}
