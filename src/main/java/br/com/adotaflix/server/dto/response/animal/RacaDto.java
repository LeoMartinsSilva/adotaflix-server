package br.com.adotaflix.server.dto.response.animal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RacaDto {
	private Long id;
	private String nome;
	private EspecieDto especie;
}
