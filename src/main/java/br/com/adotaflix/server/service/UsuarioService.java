package br.com.adotaflix.server.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.adotaflix.server.config.exception.NotFoundException;
import br.com.adotaflix.server.dto.request.AlterarUsuarioLogadoRequestDto;
import br.com.adotaflix.server.dto.request.RegistrarUsuarioRequestDto;
import br.com.adotaflix.server.dto.response.RegistrarUsuarioResponseDto;
import br.com.adotaflix.server.dto.response.UsuarioDto;
import br.com.adotaflix.server.mapper.EnderecoMapper;
import br.com.adotaflix.server.mapper.UsuarioMapper;
import br.com.adotaflix.server.model.Usuario;
import br.com.adotaflix.server.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	private final UsuarioMapper usuarioMapper;
	private final UsuarioLogadoService usuarioLogadoService;
	private final PasswordEncoder encoder;
	private final EnderecoMapper enderecoMapper;
	private final EnderecoService enderecoService;
	
	public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, UsuarioLogadoService usuarioLogadoService, PasswordEncoder encoder, EnderecoService enderecoService, EnderecoMapper enderecoMapper) {
		this.usuarioRepository = usuarioRepository;
		this.usuarioMapper = usuarioMapper;
		this.usuarioLogadoService = usuarioLogadoService;
		this.encoder = encoder;
		this.enderecoMapper = enderecoMapper;
		this.enderecoService = enderecoService;
	}
	
	public List<UsuarioDto> buscar(){
		return usuarioMapper.toDto(usuarioRepository.findAll());
	}
	
	public UsuarioDto buscarPorId(Long id){
		return usuarioMapper.toDto(usuarioRepository.findById(id).orElseThrow(()->new NotFoundException("Instituição não encontrada")));
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

	@Transactional
	public void tornarAdmin(Long id) {
		Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
		usuario.setRole("ROLE_ADMIN");
	}

	@Transactional
	public UsuarioDto alterarUsuarioLogado(AlterarUsuarioLogadoRequestDto dados) {
		Usuario usuario = usuarioLogadoService.getUsuarioLogado();
		usuario.setEndereco(enderecoService.alterar(usuario.getEndereco().getId(), dados.getEndereco()));
		usuario.setNome(dados.getNome());
		usuario.setTelefone(dados.getTelefone());
		
		return usuarioMapper.toDto(usuario);
	}
	
}
