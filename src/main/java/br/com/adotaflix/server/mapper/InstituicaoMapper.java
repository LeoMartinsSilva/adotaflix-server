package br.com.adotaflix.server.mapper;

import org.springframework.stereotype.Component;

import br.com.adotaflix.server.dto.response.InstituicaoDto;
import br.com.adotaflix.server.model.Instituicao;

@Component
public class InstituicaoMapper extends AbstractMapper<InstituicaoDto, Instituicao> {

	private final EnderecoMapper enderecoMapper;
	
	public InstituicaoMapper(EnderecoMapper enderecoMapper) {
		this.enderecoMapper = enderecoMapper;
	}
	
	@Override
	public InstituicaoDto toDto(Instituicao entity) {
		if(entity == null) return null;
		return InstituicaoDto.builder()
				.id(entity.getId())
				.nome(entity.getNome())
				.documento(entity.getDocumento())
				.telefone(entity.getTelefone())
				.telefoneWhatsapp(entity.getTelefoneWhatsapp())
				.endereco(enderecoMapper.toDto(entity.getEndereco()))
				.build();
	}

	@Override
	public Instituicao toEntity(InstituicaoDto dto) {
		if(dto == null) return null;
		return Instituicao.builder()
				.id(dto.getId())
				.nome(dto.getNome())
				.documento(dto.getDocumento())
				.telefone(dto.getTelefone())
				.telefoneWhatsapp(dto.getTelefoneWhatsapp())
				.endereco(enderecoMapper.toEntity(dto.getEndereco()))
				.build();
	}

}
