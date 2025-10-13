package br.com.adotaflix.server.dto.request.animal.raca;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrarRacaRequestDto {
	@NotBlank(message = "Nome é obrigatório")
	private String nome;

	@NotNull(message = "Espécie é obrigatória")
	private Long idEspecie;
}
