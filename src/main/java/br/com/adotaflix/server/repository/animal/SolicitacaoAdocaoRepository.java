package br.com.adotaflix.server.repository.animal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.adotaflix.server.model.animal.SolicitacaoAdocao;

public interface SolicitacaoAdocaoRepository extends JpaRepository<SolicitacaoAdocao, Long> {

	public List<SolicitacaoAdocao> findAllByUsuarioId(Long idUsuario);

	public List<SolicitacaoAdocao> findAllByAnimalInstituicaoId(Long id);

	@Query("select count(s) from SolicitacaoAdocao s where s.animal.instituicao.id=:idInstituicao and s.resposta=:resposta and EXTRACT(MONTH FROM s.dataResposta) = :mes")
	public Integer countByInstituicaoAndRespostaAndMes(Long idInstituicao, String resposta, int mes);

	@Query("select count(s) from SolicitacaoAdocao s where s.animal.instituicao.id=:idInstituicao and (s.resposta is null or s.resposta = '') ")
	public Integer countByInstituicaoAndNotResposta(Long idInstituicao);

	@Query("select count(s) from SolicitacaoAdocao s where (s.resposta is null or s.resposta = '') ")
	public Integer countByNotResposta();

}
