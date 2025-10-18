package br.com.adotaflix.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.adotaflix.server.dto.response.dashboard.AdminDashboardDto;
import br.com.adotaflix.server.dto.response.dashboard.AnimaisAdotadosPorEspecieDto;
import br.com.adotaflix.server.dto.response.dashboard.NovosUsuariosMesDto;
import br.com.adotaflix.server.repository.InstituicaoRepository;
import br.com.adotaflix.server.repository.UsuarioRepository;
import br.com.adotaflix.server.repository.animal.AnimalRepository;
import br.com.adotaflix.server.repository.animal.SolicitacaoAdocaoRepository;

@Service
public class AdminDashboardService {
	private final InstituicaoRepository instituicaoRepository;
	private final UsuarioRepository usuarioRepository;
	private final AnimalRepository animalRepository;
	private final SolicitacaoAdocaoRepository solicitacaoAdocaoRepository;

	public AdminDashboardService(
			InstituicaoRepository instituicaoRepository,
			UsuarioRepository usuarioRepository,
			AnimalRepository animalRepository,
			SolicitacaoAdocaoRepository solicitacaoAdocaoRepository) {
		this.instituicaoRepository = instituicaoRepository;
		this.usuarioRepository = usuarioRepository;
		this.animalRepository = animalRepository;
		this.solicitacaoAdocaoRepository = solicitacaoAdocaoRepository;
	}
	
	public AdminDashboardDto getDashboard() {
		AdminDashboardDto dashboard = AdminDashboardDto.builder()
				.animaisAdotados(animalRepository.countByStatus("A"))
				.animaisDisponiveis(animalRepository.countByStatus("D"))
				.instituicoesAtivas(instituicaoRepository.count())
				.totalUsuarios(usuarioRepository.count())
				.solicitacoesPendentes(solicitacaoAdocaoRepository.countByNotResposta())				
				.build();
		
		Long totalAnimais = animalRepository.count();
		if(totalAnimais>0) {

			dashboard.setPercentualAdocaoAnimais((dashboard.getAnimaisAdotados().doubleValue()/totalAnimais.doubleValue())*100);
		}
		
		List<NovosUsuariosMesDto> novosUsuariosMes = new ArrayList<NovosUsuariosMesDto>();
		List<Map<String, Object>> novosUsuariosMap = usuarioRepository.usuariosPorMes();
		for(Map<String, Object> item : novosUsuariosMap) {
			novosUsuariosMes.add(NovosUsuariosMesDto.builder()
			.mes((Integer)item.get("mes"))
			.usuarios((Long)item.get("total"))
			.build());
		}
		
		List<AnimaisAdotadosPorEspecieDto> animaisAdotadosEspecie = new ArrayList<AnimaisAdotadosPorEspecieDto>();
		List<Map<String, Object>> animaisAdotadosMap = animalRepository.adotadosPorEspecie();
		for(Map<String, Object> item : animaisAdotadosMap) {
			animaisAdotadosEspecie.add(AnimaisAdotadosPorEspecieDto.builder()
			.nomeEspecie((String)item.get("nomeEspecie"))
			.adotados((Long)item.get("adotados"))
			.build());
		}
		
		dashboard.setNovosUsuariosMes(novosUsuariosMes);
		dashboard.setAnimaisAdotadosPorEspecie(animaisAdotadosEspecie);
		return dashboard;
	}
}
