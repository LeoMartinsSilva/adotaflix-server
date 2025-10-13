package br.com.adotaflix.server.service;

import org.springframework.stereotype.Service;

import br.com.adotaflix.server.config.exception.NotFoundException;
import br.com.adotaflix.server.dto.EnderecoDto;
import br.com.adotaflix.server.mapper.EnderecoMapper;
import br.com.adotaflix.server.model.Endereco;
import br.com.adotaflix.server.repository.EnderecoRepository;
import jakarta.transaction.Transactional;

@Service
public class EnderecoService {

	private final EnderecoMapper enderecoMapper;
	private final EnderecoRepository enderecoRepository;
	
	public EnderecoService(EnderecoMapper enderecoMapper, EnderecoRepository enderecoRepository) {
		this.enderecoMapper = enderecoMapper;
		this.enderecoRepository = enderecoRepository;
	}
	
	@Transactional
	public Endereco registrar(EnderecoDto enderecoDto) {
		Endereco endereco = enderecoMapper.toEntity(enderecoDto);
		endereco = enderecoRepository.save(endereco);
		return endereco;
	}

	public Endereco alterar(Long id, EnderecoDto dados) {
		Endereco endereco = enderecoRepository.findById(id).orElseThrow(()->new NotFoundException("Endereço não encontrada"));
		
		endereco.setCep(dados.getCep());
		endereco.setLogradouro(dados.getLogradouro());
		endereco.setNumero(dados.getNumero());
		endereco.setSemNumero(dados.getSemNumero());
		endereco.setEstado(dados.getEstado());
		endereco.setCidade(dados.getCidade());
		
		return endereco;
	}
	
}
