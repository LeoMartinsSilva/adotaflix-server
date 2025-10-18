package br.com.adotaflix.server.dto.response.dashboard;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NovosUsuariosMesDto {
	private Integer mes;
	private Long usuarios;
}
