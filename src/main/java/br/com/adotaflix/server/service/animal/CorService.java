package br.com.adotaflix.server.service.animal;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.adotaflix.server.config.exception.NotFoundException;
import br.com.adotaflix.server.dto.request.animal.cor.AlterarCorRequestDto;
import br.com.adotaflix.server.dto.request.animal.cor.RegistrarCorRequestDto;
import br.com.adotaflix.server.dto.response.animal.CorDto;
import br.com.adotaflix.server.mapper.animal.CorMapper;
import br.com.adotaflix.server.model.animal.Cor;
import br.com.adotaflix.server.repository.animal.CorRepository;
import jakarta.transaction.Transactional;

@Service
public class CorService {
	private final CorRepository corRepository;
	private final CorMapper corMapper;
	
	public CorService(CorRepository corRepository, CorMapper corMapper) {
		this.corRepository = corRepository;
		this.corMapper = corMapper;
	}
	
	public List<CorDto> buscar(){
		return corMapper.toDto(corRepository.findAll());
	}
	
	public CorDto buscarPorId(Long id){
		return corMapper.toDto(corRepository.findById(id).orElseThrow(()->new NotFoundException("Cor não encontrada")));
	}
	
	
	@Transactional
	public CorDto registrar(RegistrarCorRequestDto dados) {
		Cor cor = Cor.builder()
				.nome(dados.getNome())
				.build();
		
		cor = corRepository.save(cor);
		return corMapper.toDto(cor);
	}
	
	@Transactional
	public CorDto alterar(AlterarCorRequestDto dados) {
		Cor cor = corRepository.findById(dados.getId()).orElseThrow(()->new NotFoundException("Cor não encontrada"));
		cor.setNome(dados.getNome());
		return corMapper.toDto(cor);
	}
}
