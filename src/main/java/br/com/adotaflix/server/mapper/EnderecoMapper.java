package br.com.adotaflix.server.mapper;

import org.springframework.stereotype.Component;

import br.com.adotaflix.server.dto.EnderecoDto;
import br.com.adotaflix.server.model.Endereco;

@Component
public class EnderecoMapper extends AbstractMapper<EnderecoDto, Endereco> {

	@Override
	public EnderecoDto toDto(Endereco entity) {
		return EnderecoDto.builder()
				.cep(entity.getCep())
				.logradouro(entity.getLogradouro())
				.numero(entity.getNumero())
				.semNumero(entity.getSemNumero())
				.estado(entity.getEstado())
				.cidade(entity.getCidade())
				.build();
	}

	@Override
	public Endereco toEntity(EnderecoDto dto) {
		return Endereco.builder()
				.cep(dto.getCep())
				.logradouro(dto.getLogradouro())
				.numero(dto.getNumero())
				.semNumero(dto.getSemNumero())
				.estado(dto.getEstado())
				.cidade(dto.getCidade())
				.build();
	}

}
