package br.com.adotaflix.server.dto.request.animal.cor;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrarCorRequestDto {
	@NotBlank(message = "Nome é obrigatório")
	private String nome;
}
