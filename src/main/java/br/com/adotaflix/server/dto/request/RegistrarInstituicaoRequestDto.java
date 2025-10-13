package br.com.adotaflix.server.dto.request;

import br.com.adotaflix.server.dto.EnderecoDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrarInstituicaoRequestDto {
	@NotBlank(message = "Nome é obrigatório")
	private String nome;
	
	@NotBlank(message = "Documento é obrigatório")
	@Pattern(
		    regexp = "^(\\d{3}\\.?\\d{3}\\.?\\d{3}-\\d{2}|\\d{2}\\.?\\d{3}\\.?\\d{3}/\\d{4}-\\d{2})$",
		    message = "CPF ou CNPJ inválido. Use o formato 000.000.000-00 ou 00.000.000/0000-00."
		)
	private String documento;
	
	@Pattern(
			regexp = "^\\+55\\s?\\(\\d{2}\\)\\s?(9\\d{4}|\\d{4})-\\d{4}$",
	        message = "Telefone inválido (use o formato +55(XX) 9XXXXX-XXXX ou +55(XX) XXXX-XXXX)"
	    )
	private String telefone;
	
	@Pattern(
			regexp = "^\\+55\\s?\\(\\d{2}\\)\\s?(9\\d{4}|\\d{4})-\\d{4}$",
	        message = "Telefone inválido (use o formato +55(XX) 9XXXXX-XXXX ou +55(XX) XXXX-XXXX)"
	    )
	private String telefoneWhatsapp;
	
	@Valid
	@NotNull(message = "Endereço é obrigatório")
	private EnderecoDto endereco;
}
