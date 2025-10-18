package br.com.adotaflix.server.dto.response.dashboard;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AdminDashboardDto {
	private Long totalUsuarios;
	private Long instituicoesAtivas;
	private Integer solicitacoesPendentes;
	private Integer animaisDisponiveis;
	private Integer animaisAdotados;
	private Double percentualAdocaoAnimais;
	private List<NovosUsuariosMesDto> novosUsuariosMes;
	private List<AnimaisAdotadosPorEspecieDto> animaisAdotadosPorEspecie;
}
