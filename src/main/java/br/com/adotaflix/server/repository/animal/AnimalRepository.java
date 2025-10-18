package br.com.adotaflix.server.repository.animal;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import br.com.adotaflix.server.model.animal.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long>, JpaSpecificationExecutor<Animal> {

	Integer countByInstituicaoId(Long idInstituicao);
	
	Integer countByInstituicaoIdAndStatus(Long idInstituicao, String status);

	Integer countByStatus(String string);

	@Query("select a.raca.especie.nome nomeEspecie, count(a) adotados from Animal a where a.status='A' group by a.raca.especie.nome")
	List<Map<String, Object>> adotadosPorEspecie();
	
}
