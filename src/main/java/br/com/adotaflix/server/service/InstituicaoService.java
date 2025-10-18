package br.com.adotaflix.server.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.adotaflix.server.config.exception.NotFoundException;
import br.com.adotaflix.server.dto.request.AlterarInstituicaoRequestDto;
import br.com.adotaflix.server.dto.request.RegistrarInstituicaoRequestDto;
import br.com.adotaflix.server.dto.response.InstituicaoDto;
import br.com.adotaflix.server.dto.response.RegistrarInstituicaoResponseDto;
import br.com.adotaflix.server.dto.response.dashboard.InstituicaoDashboardDto;
import br.com.adotaflix.server.mapper.EnderecoMapper;
import br.com.adotaflix.server.mapper.InstituicaoMapper;
import br.com.adotaflix.server.model.Instituicao;
import br.com.adotaflix.server.model.Usuario;
import br.com.adotaflix.server.repository.InstituicaoRepository;
import br.com.adotaflix.server.repository.UsuarioRepository;
import br.com.adotaflix.server.repository.animal.AnimalRepository;
import br.com.adotaflix.server.repository.animal.SolicitacaoAdocaoRepository;
import br.com.adotaflix.server.util.ImageUtil;
import jakarta.transaction.Transactional;

@Service
public class InstituicaoService {

	private final InstituicaoRepository instituicaoRepository;
	private final InstituicaoMapper instituicaoMapper;
	private final UsuarioRepository usuarioRepository;
	private final UsuarioLogadoService usuarioLogadoService;
	private final EnderecoMapper enderecoMapper;
	private final EnderecoService enderecoService;
	private final ImageUtil imageUtil;
	private final AnimalRepository animalRepository;
	private final SolicitacaoAdocaoRepository solicitacaoAdocaoRepository;

	public InstituicaoService(
			InstituicaoRepository instituicaoRepository, 
			InstituicaoMapper instituicaoMapper,
			UsuarioRepository usuarioRepository, 
			UsuarioLogadoService usuarioLogadoService,
			EnderecoService enderecoService, 
			EnderecoMapper enderecoMapper, 
			ImageUtil imageUtil, 
			AnimalRepository animalRepository,
			SolicitacaoAdocaoRepository solicitacaoAdocaoRepository) {
		this.instituicaoRepository = instituicaoRepository;
		this.instituicaoMapper = instituicaoMapper;
		this.usuarioRepository = usuarioRepository;
		this.usuarioLogadoService = usuarioLogadoService;
		this.enderecoMapper = enderecoMapper;
		this.enderecoService = enderecoService;
		this.imageUtil = imageUtil;
		this.animalRepository = animalRepository;
		this.solicitacaoAdocaoRepository = solicitacaoAdocaoRepository;
	}

	@Value("${images.dir}")
	private String imagesDir;

	public List<InstituicaoDto> buscar() {
		return instituicaoMapper.toDto(instituicaoRepository.findAll());
	}

	public InstituicaoDto buscarPorId(Long id) {
		return instituicaoMapper.toDto(instituicaoRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Instituição não encontrada")));
	}

	public RegistrarInstituicaoResponseDto registrar(RegistrarInstituicaoRequestDto dados) {
		Instituicao instituicao = Instituicao.builder().nome(dados.getNome()).documento(dados.getDocumento())
				.telefone(dados.getTelefone()).telefoneWhatsapp(dados.getTelefoneWhatsapp())
				.endereco(enderecoService.registrar(dados.getEndereco())).build();

		instituicao = instituicaoRepository.save(instituicao);

		return RegistrarInstituicaoResponseDto.builder().id(instituicao.getId()).nome(instituicao.getNome())
				.documento(instituicao.getDocumento()).telefone(instituicao.getTelefone())
				.telefoneWhatsapp(instituicao.getTelefoneWhatsapp())
				.endereco(enderecoMapper.toDto(instituicao.getEndereco())).build();
	}

	@Transactional
	public InstituicaoDto alterar(AlterarInstituicaoRequestDto dados) {
		Instituicao instituicao = instituicaoRepository.findById(dados.getId())
				.orElseThrow(() -> new NotFoundException("Instituicao não encontrada"));

		instituicao.setNome(dados.getNome());
		instituicao.setDocumento(dados.getDocumento());
		instituicao.setTelefone(dados.getTelefone());
		instituicao.setTelefoneWhatsapp(dados.getTelefoneWhatsapp());
		instituicao.setEndereco(enderecoService.alterar(instituicao.getEndereco().getId(), dados.getEndereco()));
		return instituicaoMapper.toDto(instituicao);
	}

	@Transactional
	public void adicionarUsuario(Long idInstituicao, Long idUsuario) {
		Usuario usuario = usuarioRepository.findById(idUsuario)
				.orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
		usuario.setInstituicao(Instituicao.builder().id(idInstituicao).build());
	}

	@Transactional
	public void adicionarUsuario(Long idUsuario) {
		Usuario usuarioLogado = usuarioLogadoService.getUsuarioLogado();
		if (usuarioLogado == null || usuarioLogado.getInstituicao() == null) {
			throw new RuntimeException("Você não está registrado em nenhuma instituição");
		}

		Usuario usuario = usuarioRepository.findById(idUsuario)
				.orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
		usuario.setInstituicao(Instituicao.builder().id(usuarioLogado.getInstituicao().getId()).build());

	}

	public void inserirImagem(Long idInstituicao, MultipartFile file) {
		Instituicao instituicao = instituicaoRepository.findById(idInstituicao)
				.orElseThrow(() -> new NotFoundException("Instituicao não encontrada"));

		Usuario usuario = usuarioLogadoService.getUsuarioLogado();
		if (usuario.getInstituicao() == null) {
			throw new RuntimeException("Você não está registrado em nenhuma instituição");
		}
		if (!usuario.getInstituicao().getId().equals(instituicao.getId())) {
			throw new RuntimeException("Esse animal não pertence a sua instituição");
		}

		File pasta = new File(imagesDir + "/instituicao/" + idInstituicao.toString());
		if (pasta.exists() && pasta.isDirectory()) {
			for (File arquivo : pasta.listFiles()) {
				if (arquivo.isFile()) {
					arquivo.delete();
				}
			}
		}
		if(file==null) return;
		Path caminhoDestino = Paths.get(imagesDir + "/instituicao/" + idInstituicao, file.getOriginalFilename());
		try {
			if (!pasta.exists()) {
				pasta.mkdirs();
			}
			Files.write(caminhoDestino, file.getBytes());
		} catch (IOException e) {
			throw new RuntimeException("Erro ao salvar imagem", e);
		}

	}

	public String buscarImagem(Long idInstituicao) {
		return imageUtil.getImagem("/instituicao", idInstituicao);
	}
	
	public InstituicaoDashboardDto buscarDadosDashboard() {
		Usuario usuario = usuarioLogadoService.getUsuarioLogado();
		if (usuario.getInstituicao() == null) {
			throw new RuntimeException("Você não está registrado em nenhuma instituição");
		}
		return InstituicaoDashboardDto.builder()
		.animaisCadastrados(animalRepository.countByInstituicaoId(usuario.getInstituicao().getId()))
		.animaisAdotados(animalRepository.countByInstituicaoIdAndStatus(usuario.getInstituicao().getId(), "A"))
		.animaisDisponiveis(animalRepository.countByInstituicaoIdAndStatus(usuario.getInstituicao().getId(), "D"))
		.adocoesMes(solicitacaoAdocaoRepository.countByInstituicaoAndRespostaAndMes(usuario.getInstituicao().getId(), "A", LocalDate.now().getMonth().getValue() ))
		.solicitacoesPendetes(solicitacaoAdocaoRepository.countByInstituicaoAndNotResposta(usuario.getInstituicao().getId()))
		.build();
	
	}

}
