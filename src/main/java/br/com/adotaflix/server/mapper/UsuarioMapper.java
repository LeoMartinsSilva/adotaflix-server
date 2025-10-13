package br.com.adotaflix.server.mapper;

import org.springframework.stereotype.Component;

import br.com.adotaflix.server.dto.response.UsuarioDto;
import br.com.adotaflix.server.model.Usuario;

@Component
public class UsuarioMapper extends AbstractMapper<UsuarioDto, Usuario> {

	private final EnderecoMapper enderecoMapper;
	private final InstituicaoMapper instituicaoMapper;
	
	public UsuarioMapper(EnderecoMapper enderecoMapper, InstituicaoMapper instituicaoMapper) {
		this.enderecoMapper = enderecoMapper;
		this.instituicaoMapper = instituicaoMapper;
	}
	
	@Override
	public UsuarioDto toDto(Usuario entity) {
		if(entity == null) return null;
		return UsuarioDto.builder()
				.id(entity.getId())
				.nome(entity.getNome())
				.email(entity.getEmail())
				.telefone(entity.getTelefone())
				.instituicao(instituicaoMapper.toDto(entity.getInstituicao()))
				.endereco(enderecoMapper.toDto(entity.getEndereco()))
				.build();
	}

	@Override
	public Usuario toEntity(UsuarioDto dto) {
		if(dto == null) return null;
		return Usuario.builder()
				.id(dto.getId())
				.nome(dto.getNome())
				.email(dto.getEmail())
				.telefone(dto.getTelefone())
				.instituicao(instituicaoMapper.toEntity(dto.getInstituicao()))
				.endereco(enderecoMapper.toEntity(dto.getEndereco()))
				.build();
	}

}
