package br.com.adotaflix.server.dto.response;

import br.com.adotaflix.server.dto.EnderecoDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UsuarioDto {
	private Long id;
	private String nome;
	private String email;
	private EnderecoDto endereco;
	private InstituicaoDto instituicao;
	private String telefone;
}
