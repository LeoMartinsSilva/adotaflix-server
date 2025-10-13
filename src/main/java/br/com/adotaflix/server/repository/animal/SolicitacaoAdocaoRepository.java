package br.com.adotaflix.server.repository.animal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.adotaflix.server.model.animal.SolicitacaoAdocao;

public interface SolicitacaoAdocaoRepository extends JpaRepository<SolicitacaoAdocao, Long> {

	public List<SolicitacaoAdocao> findAllByUsuarioId(Long idUsuario);

	public List<SolicitacaoAdocao> findAllByAnimalInstituicaoId(Long id);

}
