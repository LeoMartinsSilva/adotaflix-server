package br.com.adotaflix.server.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "endereco")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_endereco")
	private Long id;
	
	@Column(name="ds_cep")
	private String cep;
	
	@Column(name="ds_logradouro")
	private String logradouro;
	
	@Column(name="ds_numero")
	private Integer numero;
	
	@Column(name="fl_sem_numero")
	private Boolean semNumero;
	
	@Column(name="ds_estado")
	private String estado;
	
	@Column(name="ds_cidade")
	private String cidade;

}
