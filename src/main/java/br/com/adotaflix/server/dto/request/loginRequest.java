package br.com.adotaflix.server.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record loginRequest(
		@NotEmpty(message = "Email não pode ser vazio.")
		String email,

		@NotEmpty(message = "Senha não pode ser vazia.")
		String password
) {
}
