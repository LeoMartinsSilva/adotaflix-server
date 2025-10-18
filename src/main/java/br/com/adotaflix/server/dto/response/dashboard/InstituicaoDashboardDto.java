package br.com.adotaflix.server.dto.response.dashboard;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InstituicaoDashboardDto {
	private Integer animaisCadastrados;
	private Integer solicitacoesPendetes;
	private Integer adocoesMes;
	private Integer animaisDisponiveis;
	private Integer animaisAdotados;
}
