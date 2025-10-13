package br.com.adotaflix.server.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SolicitarAdocaoRequestDto {
	@NotNull(message="O Id do animal é obrigatório")
	private Long idAnimal;
	
}
