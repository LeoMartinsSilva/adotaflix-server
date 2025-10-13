package br.com.adotaflix.server.dto.request.animal.especie;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrarEspecieRequestDto {
	@NotBlank(message = "Nome é obrigatório")
	private String nome;
}
