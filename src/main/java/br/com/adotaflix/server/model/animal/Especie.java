package br.com.adotaflix.server.model.animal;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "especie")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Especie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_especie")
	private Long id;
	
	@Column(name="nm_especie")
	private String nome;
	
	@OneToMany(mappedBy="especie")
	private List<Raca> racas;

}
