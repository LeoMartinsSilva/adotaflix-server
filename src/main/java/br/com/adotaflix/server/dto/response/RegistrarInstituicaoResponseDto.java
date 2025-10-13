package br.com.adotaflix.server.dto.response;

import br.com.adotaflix.server.dto.EnderecoDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrarInstituicaoResponseDto {
	private Long id;
	private String nome;
	private String documento;
	private String telefone;
	private String telefoneWhatsapp;
	private EnderecoDto endereco;
}
