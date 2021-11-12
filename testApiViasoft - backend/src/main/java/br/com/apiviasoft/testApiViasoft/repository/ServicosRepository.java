package br.com.apiviasoft.testApiViasoft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.apiviasoft.testApiViasoft.model.ServicosNF;

@Repository
public interface ServicosRepository extends JpaRepository<ServicosNF, Long> {

	// REST do status atual do serviço filtrando por estado
	@Query(value = "SELECT MAX(id) AS id, autorizador, status_servico, MAX(historico_tempo)	AS historico_tempo FROM servicosnf	GROUP BY autorizador, status_servico ORDER BY autorizador ASC LIMIT 16;", nativeQuery = true)
	public List<ServicosNF> findAllByStatusIgnoreCase();

	// REST dos status atual dos serviços por estado
	@Query(value = "SELECT MAX(id) AS id, autorizador, status_servico, MAX(historico_tempo) AS historico_tempo FROM servicosnf WHERE autorizador = ?1 GROUP BY autorizador, status_servico ORDER BY historico_tempo DESC LIMIT 1;", nativeQuery = true)
	public List<ServicosNF> findByUfContainingIgnoreCase(String autorizador);

	// REST de qual estado teve mais indisponibilidade de serviço
	@Query(value = "SELECT p.autorizador FROM servicosnf AS p JOIN (SELECT autorizador, COUNT(status_servico) FROM servicosnf WHERE status_servico = \"OFF\" GROUP BY autorizador ORDER BY COUNT(status_servico) DESC LIMIT 1) AS s ON s.autorizador = p.autorizador LIMIT 1;", nativeQuery = true)
	public List<String> findByIndisponibilidade();

	// REST dos status dos serviços por estado filtrando por data
	@Query(value = "SELECT * FROM servicosnf WHERE STR_TO_DATE(historico_tempo, '%Y-%m-%d') = STR_TO_DATE(?1, '%Y-%m-%d');", nativeQuery = true)
	public List<ServicosNF> findByHistoricoTempo(String historicoTempo);
}
