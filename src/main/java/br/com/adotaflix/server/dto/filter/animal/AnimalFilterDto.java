package br.com.adotaflix.server.dto.filter.animal;

import br.com.adotaflix.server.dto.filter.FilterIdDto;
import br.com.adotaflix.server.dto.filter.FilterMultiSelectDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnimalFilterDto {
	private FilterIdDto especie;
	private FilterIdDto raca;
	private FilterIdDto cor;
	private FilterMultiSelectDto porte;
	private FilterIdDto instituicao;
}
