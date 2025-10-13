package br.com.adotaflix.server.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.adotaflix.server.config.exception.NotFoundException;
import br.com.adotaflix.server.dto.request.AlterarInstituicaoRequestDto;
import br.com.adotaflix.server.dto.request.RegistrarInstituicaoRequestDto;
import br.com.adotaflix.server.dto.response.InstituicaoDto;
import br.com.adotaflix.server.dto.response.RegistrarInstituicaoResponseDto;
import br.com.adotaflix.server.mapper.EnderecoMapper;
import br.com.adotaflix.server.mapper.InstituicaoMapper;
import br.com.adotaflix.server.model.Instituicao;
import br.com.adotaflix.server.model.Usuario;
import br.com.adotaflix.server.repository.InstituicaoRepository;
import br.com.adotaflix.server.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class InstituicaoService {

	private final InstituicaoRepository instituicaoRepository;
	private final InstituicaoMapper instituicaoMapper;
	private final UsuarioRepository usuarioRepository;
	private final UsuarioLogadoService usuarioLogadoService;
	private final EnderecoMapper enderecoMapper;
	private final EnderecoService enderecoService;
	
	public InstituicaoService(InstituicaoRepository instituicaoRepository, InstituicaoMapper instituicaoMapper, UsuarioRepository usuarioRepository, UsuarioLogadoService usuarioLogadoService, EnderecoService enderecoService, EnderecoMapper enderecoMapper) {
		this.instituicaoRepository = instituicaoRepository;
		this.instituicaoMapper = instituicaoMapper;
		this.usuarioRepository = usuarioRepository;
		this.usuarioLogadoService = usuarioLogadoService;
		this.enderecoMapper = enderecoMapper;
		this.enderecoService = enderecoService;
	}
	
	public List<InstituicaoDto> buscar(){
		return instituicaoMapper.toDto(instituicaoRepository.findAll());
	}
	
	public InstituicaoDto buscarPorId(Long id){
		return instituicaoMapper.toDto(instituicaoRepository.findById(id).orElseThrow(()->new NotFoundException("Instituição não encontrada")));
	}
	
	public RegistrarInstituicaoResponseDto registrar(RegistrarInstituicaoRequestDto dados) {
		Instituicao instituicao = Instituicao.builder()
				.nome(dados.getNome())
				.documento(dados.getDocumento())
				.telefone(dados.getTelefone())
				.telefoneWhatsapp(dados.getTelefoneWhatsapp())
				.endereco(enderecoService.registrar(dados.getEndereco()))
				.build();
		
		instituicao = instituicaoRepository.save(instituicao);
		
		return RegistrarInstituicaoResponseDto.builder()
				.id(instituicao.getId())
				.nome(instituicao.getNome())
				.documento(instituicao.getDocumento())
				.telefone(instituicao.getTelefone())
				.telefoneWhatsapp(instituicao.getTelefoneWhatsapp())
				.endereco(enderecoMapper.toDto(instituicao.getEndereco()))
				.build();
	}
	
	@Transactional
	public InstituicaoDto alterar(AlterarInstituicaoRequestDto dados) {
		Instituicao instituicao = instituicaoRepository.findById(dados.getId()).orElseThrow(()->new NotFoundException("Instituicao não encontrada"));
		
		instituicao.setNome(dados.getNome());
		instituicao.setDocumento(dados.getDocumento());
		instituicao.setTelefone(dados.getTelefone());
		instituicao.setTelefoneWhatsapp(dados.getTelefoneWhatsapp());
		instituicao.setEndereco(enderecoService.alterar(instituicao.getEndereco().getId(), dados.getEndereco()));
		return instituicaoMapper.toDto(instituicao);
	}

	@Transactional
	public void adicionarUsuario(Long idInstituicao, Long idUsuario) {
		Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(()->new NotFoundException("Usuário não encontrado"));
		usuario.setInstituicao(Instituicao.builder().id(idInstituicao).build());
	}
	
	@Transactional
	public void adicionarUsuario(Long idUsuario) {
		Usuario usuarioLogado = usuarioLogadoService.getUsuarioLogado();
		if(usuarioLogado==null || usuarioLogado.getInstituicao()==null) {
			throw new RuntimeException("Você não está registrado em nenhuma instituição");
		}
		
		Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(()->new NotFoundException("Usuário não encontrado"));
		usuario.setInstituicao(Instituicao.builder().id(usuarioLogado.getInstituicao().getId()).build());
		
	}
}
