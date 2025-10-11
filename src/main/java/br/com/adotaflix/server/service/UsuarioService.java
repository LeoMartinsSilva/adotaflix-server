package br.com.adotaflix.server.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.adotaflix.server.dto.request.RegistrarUsuarioRequestDto;
import br.com.adotaflix.server.dto.response.RegistrarUsuarioResponseDto;
import br.com.adotaflix.server.mapper.EnderecoMapper;
import br.com.adotaflix.server.model.Usuario;
import br.com.adotaflix.server.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder encoder;
	private final EnderecoMapper enderecoMapper;
	private final EnderecoService enderecoService;
	
	public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder encoder, EnderecoService enderecoService, EnderecoMapper enderecoMapper) {
		this.usuarioRepository = usuarioRepository;
		this.encoder = encoder;
		this.enderecoMapper = enderecoMapper;
		this.enderecoService = enderecoService;
	}
	
	@Transactional
	public RegistrarUsuarioResponseDto registrar(RegistrarUsuarioRequestDto dados) {
		Usuario usuario = Usuario.builder()
				.nome(dados.getNome())
				.email(dados.getEmail())
				.senha(encoder.encode(dados.getSenha()))
				.telefone(dados.getTelefone())
				.endereco(enderecoService.registrar(dados.getEndereco()))
				.dataCadastro(LocalDateTime.now())
				.role("ROLE_USER")
				.build();
		
		usuario = usuarioRepository.save(usuario);
		return RegistrarUsuarioResponseDto.builder()
				.id(usuario.getId())
				.nome(usuario.getNome())
				.email(usuario.getEmail())
				.endereco(enderecoMapper.toDto(usuario.getEndereco()))
				.telefone(usuario.getTelefone())
				.build();
	}
	
}
