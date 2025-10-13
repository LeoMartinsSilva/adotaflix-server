package br.com.adotaflix.server.model.animal;

import java.time.LocalDate;

import br.com.adotaflix.server.model.Instituicao;
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
@Table(name= "animal")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Animal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_animal")
	private Long id;
	
	@Column(name="nm_animal")
	private String nome;
	
	@Column(name="ds_animal")
	private String descricao;
	
	@Column(name="dt_nascimento")
	private LocalDate dataNascimento;
	
	@JoinColumn(name="id_raca", referencedColumnName = "id_raca")
	@ManyToOne
	private Raca raca;
	
	@JoinColumn(name="id_cor", referencedColumnName = "id_cor")
	@ManyToOne
	private Cor cor;
	
	@JoinColumn(name="id_instituicao", referencedColumnName = "id_instituicao")
	@ManyToOne
	private Instituicao instituicao;
	
	@Column(name="fl_porte")
	private String porte;
	
	@Column(name="fl_status")
	private String status;
	
	@Column(name="fl_sexo")
	private String sexo;
	
}
