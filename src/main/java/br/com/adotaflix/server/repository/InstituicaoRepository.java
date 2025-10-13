package br.com.adotaflix.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.adotaflix.server.model.Instituicao;

public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {

}
