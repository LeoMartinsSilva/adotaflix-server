package br.com.adotaflix.server.service;

import org.springframework.stereotype.Service;

import br.com.adotaflix.server.dto.EnderecoDto;
import br.com.adotaflix.server.mapper.EnderecoMapper;
import br.com.adotaflix.server.model.Endereco;
import br.com.adotaflix.server.repository.EnderecoRepository;

@Service
public class EnderecoService {

	private final EnderecoMapper enderecoMapper;
	private final EnderecoRepository enderecoRepository;
	
	public EnderecoService(EnderecoMapper enderecoMapper, EnderecoRepository enderecoRepository) {
		this.enderecoMapper = enderecoMapper;
		this.enderecoRepository = enderecoRepository;
	}
	
	public Endereco registrar(EnderecoDto enderecoDto) {
		Endereco endereco = enderecoMapper.toEntity(enderecoDto);
		endereco = enderecoRepository.save(endereco);
		return endereco;
	}
	
}
