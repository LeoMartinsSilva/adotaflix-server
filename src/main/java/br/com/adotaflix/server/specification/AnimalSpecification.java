package br.com.adotaflix.server.specification;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import br.com.adotaflix.server.dto.filter.FilterIdDto;
import br.com.adotaflix.server.dto.filter.FilterMultiSelectDto;
import br.com.adotaflix.server.dto.filter.animal.AnimalFilterDto;
import br.com.adotaflix.server.model.animal.Animal;
import br.com.adotaflix.server.repository.animal.AnimalRepository;

@Component
public class AnimalSpecification {
	
	private final AnimalRepository animalRepository;
	
	public AnimalSpecification(AnimalRepository animalRepository) {
		this.animalRepository = animalRepository;
	}
	
	public List<Animal> pesquisar(AnimalFilterDto filters) {
		Specification<Animal> specification = filtrar(filters);
		return animalRepository.findAll(specification);
	}
	
	private Specification<Animal> filtrar(AnimalFilterDto filters){
		Specification<Animal> specification = filtrarDisponibilidade();
		if(filters == null) {
			return specification;
		}

		if (filters.getEspecie() != null) {
			specification = specification.and(filtrarEspecie(filters.getEspecie()));
		}
		if (filters.getRaca() != null) {
			specification = specification.and(filtrarRaca(filters.getRaca()));
		}
		if(filters.getCor() != null) {
			specification = specification.and(filtrarCor(filters.getCor()));
		}
		if(filters.getPorte() != null) {
			specification = specification.and(filtrarPorte(filters.getPorte()));
		}
		if(filters.getInstituicao() != null) {
			specification = specification.and(filtrarInstituicao(filters.getInstituicao()));	
		}
		return specification;
	}
	
	public static Specification<Animal> filtrarDisponibilidade() {
		return (root, query, builder) -> {
			return builder.equal(root.get("status"), "D");
		};
	}
	
	public static Specification<Animal> filtrarEspecie(FilterIdDto filter) {
		return (root, query, builder) -> {
			return FilterIdDto.getPredicate(root, query, builder, filter,
					root.get("raca").get("especie").get("id"));
		};
	}
	
	public static Specification<Animal> filtrarRaca(FilterIdDto filter) {
		return (root, query, builder) -> {
			return FilterIdDto.getPredicate(root, query, builder, filter,
					root.get("raca").get("id"));
		};
	}
	
	public static Specification<Animal> filtrarCor(FilterIdDto filter) {
		return (root, query, builder) -> {
			return FilterIdDto.getPredicate(root, query, builder, filter,
					root.get("cor").get("id"));
		};
	}
	
	public static Specification<Animal> filtrarInstituicao(FilterIdDto filter) {
		return (root, query, builder) -> {
			return FilterIdDto.getPredicate(root, query, builder, filter,
					root.get("instituicao").get("id"));
		};
	}
	
	public static Specification<Animal> filtrarPorte(FilterMultiSelectDto filter) {
		return (root, query, builder) -> {
			return FilterMultiSelectDto.getPredicate(root, query, builder, filter,
					root.get("porte"));
		};
	}

}
