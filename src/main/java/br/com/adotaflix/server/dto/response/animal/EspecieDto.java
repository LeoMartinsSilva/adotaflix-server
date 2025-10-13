package br.com.adotaflix.server.dto.response.animal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EspecieDto {
	private Long id;
	private String nome;
}
