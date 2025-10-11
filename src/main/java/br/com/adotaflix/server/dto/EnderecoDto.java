package br.com.adotaflix.server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EnderecoDto {
	@NotBlank(message="Cep é obrigatório")
	@Pattern(
	        regexp = "^[0-9]{5}-?[0-9]{3}$",
	        message = "CEP inválido (use o formato 99999-999 ou 99999999)"
	    )
	private String cep;
	
	@NotBlank(message="Logradouro é obrigatorio")
	private String logradouro;
	
	private Integer numero;
	private Boolean semNumero;
	
	@NotBlank(message="Estado é obrigatório")
	private String estado;
	
	@NotBlank(message="Cidade é obrigatória")
	private String cidade;

}