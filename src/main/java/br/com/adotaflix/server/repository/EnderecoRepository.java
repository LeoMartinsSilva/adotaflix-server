package br.com.adotaflix.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.adotaflix.server.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
