package br.com.adotaflix.server.dto.response;

import br.com.adotaflix.server.dto.EnderecoDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrarUsuarioResponseDto {
	private Long id;
	private String nome;
	private String email;
	private EnderecoDto endereco;
	private String telefone;
}
