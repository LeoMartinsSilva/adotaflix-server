package br.com.adotaflix.server.dto.request;

import br.com.adotaflix.server.config.enumConfig.EnumValue;
import br.com.adotaflix.server.enums.RespostaSolicitacaoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponderSolicitacaoAdocaoRequestDto {
	@NotNull(message="O Id da solicitação é obrigatório")
	private Long idSolicitacao;
	
	@NotBlank(message="A resposta é obrigatória")
	@EnumValue(enumClass = RespostaSolicitacaoEnum.class, message = "A resposta deve ser 'A' ou 'R'")
	private String resposta;
	
	@NotBlank(message="A descrição da resposta é obrigatória")
	private String descricaoResposta;
	
}
