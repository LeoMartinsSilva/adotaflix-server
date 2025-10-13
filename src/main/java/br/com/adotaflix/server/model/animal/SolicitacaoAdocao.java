package br.com.adotaflix.server.model.animal;

import java.time.LocalDateTime;

import br.com.adotaflix.server.model.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "solicitacao_adocao")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolicitacaoAdocao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_solicitacao_adocao")
	private Long id;
	
	@JoinColumn(name="id_usuario", referencedColumnName = "id_usuario")
	@ManyToOne
	private Usuario usuario;
	
	@JoinColumn(name="id_animal", referencedColumnName = "id_animal")
	@ManyToOne
	private Animal animal;

	@Column(name="dt_solicitacao")
	private LocalDateTime dataSolicitacao;
	
	@Column(name="fl_resposta")
	private String resposta;
	
	@Column(name="ds_resposta")
	private String descricaoResposta;
	
	@Column(name="dt_resposta")
	private LocalDateTime dataResposta;
	
	
}
