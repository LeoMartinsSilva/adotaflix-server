package br.com.adotaflix.server.dto.request.animal.cor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AlterarCorRequestDto {
	@NotNull(message = "É necessário informar o Id para alterar")
	Long id;
	@NotBlank(message = "Nome é obrigatório")
	private String nome;
}
