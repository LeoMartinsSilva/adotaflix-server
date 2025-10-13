package br.com.adotaflix.server.dto.response.animal;

import java.time.LocalDateTime;

import br.com.adotaflix.server.dto.response.UsuarioDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SolicitacaoAdocaoDto {
	private Long id;
	private UsuarioDto usuario;
	private AnimalDto animal;
	private LocalDateTime dataSolicitacao;
	private String resposta;
	private String descricaoResposta;
	private LocalDateTime dataResposta;
}
