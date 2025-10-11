package br.com.adotaflix.server.dto.request;

import br.com.adotaflix.server.dto.EnderecoDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrarUsuarioRequestDto {
	@NotBlank(message="O nome é obrigatório")
	private String nome;
	
	@NotBlank(message = "O e-mail é obrigatório")
	@Email(message = "E-mail inválido")
	private String email;
	
	@Size(min = 6)
	private String senha;
	
	@NotNull(message = "O endereço é obrigatório")
	@Valid
	private EnderecoDto endereco;
	
	@Pattern(
			regexp = "^\\+55\\s?\\(\\d{2}\\)\\s?(9\\d{4}|\\d{4})-\\d{4}$",
	        message = "Telefone inválido (use o formato +55(XX) 9XXXXX-XXXX ou +55(XX) XXXX-XXXX)"
	    )
	private String telefone;
}
