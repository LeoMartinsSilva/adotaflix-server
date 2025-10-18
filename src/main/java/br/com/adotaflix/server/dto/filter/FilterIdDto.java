package br.com.adotaflix.server.dto.filter;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import br.com.adotaflix.server.enums.filter.IdFilterTypeEnum;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ComponentScan
public class FilterIdDto {
	private Long value;
	private IdFilterTypeEnum type;
	
	
	public static Predicate getPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder, FilterIdDto filter, Path<Long> campo){
		if(filter.getValue()!=null) {
			if(filter.getType().equals(IdFilterTypeEnum.EQUALS)) {
				return builder.equal(campo, filter.getValue());
			}
			if(filter.getType().equals(IdFilterTypeEnum.NOTEQUALS)) {
				return builder.notEqual(campo, filter.getValue());
			}
		}
		
		return null;
	}
}
