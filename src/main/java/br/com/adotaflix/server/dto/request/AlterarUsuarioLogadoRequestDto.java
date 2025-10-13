package br.com.adotaflix.server.dto.request;

import br.com.adotaflix.server.dto.EnderecoDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlterarUsuarioLogadoRequestDto {
	@NotBlank(message="O nome é obrigatório")
	private String nome;
	
	@NotNull(message = "O endereço é obrigatório")
	@Valid
	private EnderecoDto endereco;
	
	@Pattern(
			regexp = "^\\+55\\s?\\(\\d{2}\\)\\s?(9\\d{4}|\\d{4})-\\d{4}$",
	        message = "Telefone inválido (use o formato +55(XX) 9XXXXX-XXXX ou +55(XX) XXXX-XXXX)"
	    )
	private String telefone;
}
