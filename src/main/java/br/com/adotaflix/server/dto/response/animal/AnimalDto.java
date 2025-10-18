package br.com.adotaflix.server.dto.response.animal;

import java.time.LocalDate;
import java.util.List;

import br.com.adotaflix.server.dto.response.InstituicaoDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AnimalDto {
	private Long id;
	private String nome;
	private String descricao;
	private LocalDate dataNascimento;
	private RacaDto raca;
	private CorDto cor;
	private InstituicaoDto instituicao;
	private String porte;
	private String status;
	private String sexo;
	private List<String> imagens;
}
