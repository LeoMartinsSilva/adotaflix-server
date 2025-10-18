package br.com.adotaflix.server.dto.filter;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import br.com.adotaflix.server.enums.filter.MultiSelectFilterTypeEnum;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaBuilder.In;
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
public class FilterMultiSelectDto {
	private List<String> values;
	private MultiSelectFilterTypeEnum type;
	
	public static Predicate getPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder, FilterMultiSelectDto filter, Path<String> campo) {
		if(filter.getValues()!=null && filter.getValues().size()>0) {
			if(filter.getType().equals(MultiSelectFilterTypeEnum.IN)) {
				In<String> inClause = builder.in(campo);
				for(String value: filter.getValues()) {
					inClause.value(value);
				}
				return builder.and(inClause);
			}
			if(filter.getType().equals(MultiSelectFilterTypeEnum.NOTEQUALS)) {
				In<String> inClause = builder.in(campo);
				for(String value: filter.getValues()) {
					inClause.value(value);
				}
				return builder.not(inClause);
			}
		}
		
		
		return null;
	}
}
