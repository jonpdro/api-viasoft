package br.com.apiviasoft.testApiViasoft.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apiviasoft.testApiViasoft.model.ServicosNF;
import br.com.apiviasoft.testApiViasoft.repository.ServicosRepository;
import br.com.apiviasoft.testApiViasoft.webscraper.WebScrapping;

@Service
public class ServicosService {

	@Autowired
	private ServicosRepository repository;
	
	// Service para pegar a raspagem de dados feita pelo Scraper e salvar no banco de dados
	public void extrairDados() throws IOException {
		List<ServicosNF> notasFiscais = WebScrapping.getTableHTML();

		for (ServicosNF NF : notasFiscais) {
			repository.save(NF);
		}
	}
		
	// Service do respository REST do status atual do serviço filtrando por estado
	public List<ServicosNF> findByEstado(String autorizador) {
		return repository.findByUfContainingIgnoreCase(autorizador);
	}

	// Service do respository REST dos status atual dos serviços por estado
	public List<ServicosNF> findByStatus() {
		return repository.findAllByStatusIgnoreCase();
	}

	// Service do respository REST de qual estado teve mais indisponibilidade de serviço
	public List<String> findByIndisponibilidade() {
		return repository.findByIndisponibilidade();
	}

	// Service do respository REST dos status dos serviços por estado filtrando por data
	public List<ServicosNF> findByHistoricoTempo(String historicoTempo) {
		return repository.findByHistoricoTempo(historicoTempo);
	}
}
