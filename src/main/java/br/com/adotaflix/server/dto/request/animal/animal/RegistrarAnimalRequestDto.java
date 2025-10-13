package br.com.adotaflix.server.dto.request.animal.animal;

import java.time.LocalDate;

import br.com.adotaflix.server.config.enumConfig.EnumValue;
import br.com.adotaflix.server.enums.PorteEnum;
import br.com.adotaflix.server.enums.SexoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrarAnimalRequestDto {
	@NotBlank(message = "Nome é obrigatório")
	private String nome;

	@NotBlank(message = "descrição é obrigatória")
	private String descricao;
	
	@NotNull(message = "Raça é obrigatória")
	private Long idRaca;
	
	@NotNull(message = "Cor é obrigatória")
	private Long idCor;
	
	private LocalDate dataNascimento;
	
	@NotBlank(message="Porte é obrigatório")
	@EnumValue(enumClass = PorteEnum.class, message = "Porte deve ser 'P', 'M' ou 'G'")
	private String porte;

	@NotBlank(message="Sexo é obrigatório")
	@EnumValue(enumClass = SexoEnum.class, message = "Sexo deve ser 'M' ou 'F'")
	private String sexo;
}
