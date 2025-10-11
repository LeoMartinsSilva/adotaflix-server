package br.com.adotaflix.server.mapper;

import org.springframework.stereotype.Component;

@Component
public interface EntityMapper<DTO, Entity> {

	Entity toEntity(DTO dto);

	DTO toDto(Entity entity);

}
