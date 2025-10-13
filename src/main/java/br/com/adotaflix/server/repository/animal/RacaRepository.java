package br.com.adotaflix.server.repository.animal;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.adotaflix.server.model.animal.Raca;

public interface RacaRepository extends JpaRepository<Raca, Long> {
}
