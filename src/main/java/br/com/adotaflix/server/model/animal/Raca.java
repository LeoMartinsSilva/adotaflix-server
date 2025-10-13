package br.com.adotaflix.server.model.animal;

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
@Table(name= "raca")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Raca {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_raca")
	private Long id;
	
	@Column(name="nm_raca")
	private String nome;
	
	@JoinColumn(name="id_especie", referencedColumnName = "id_especie")
	@ManyToOne
	private Especie especie;

}
