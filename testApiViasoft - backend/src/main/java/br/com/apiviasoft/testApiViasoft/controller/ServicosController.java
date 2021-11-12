package br.com.apiviasoft.testApiViasoft.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.apiviasoft.testApiViasoft.model.ServicosNF;
import br.com.apiviasoft.testApiViasoft.service.ServicosService;

@RestController
@RequestMapping("/api")
@Component
@EnableScheduling
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ServicosController {

	@Autowired
	private ServicosService service;

	// Definindo tempo de 5 minutos para o agendamento do job
	private final static long tempo = 1000 * 300;

	// Job para executar o Scraper a cada 5 minutos e salvar no banco de dados
	@Scheduled(fixedDelay = tempo)
	public String getTabelaNF() throws IOException {
		service.extrairDados();
		return null;
	}
	
	// GET do status atual do serviço filtrando por estado
	@GetMapping("/uf/{autorizador}")
	public ResponseEntity<List<ServicosNF>> getByEstado(@PathVariable String autorizador) {
		return ResponseEntity.ok(service.findByEstado(autorizador));
	}

	// GET dos status atual dos serviços por estado
	@GetMapping()
	public ResponseEntity<List<ServicosNF>> getByStatus() {
		return ResponseEntity.ok(service.findByStatus());
	}

	// GET de qual estado teve mais indisponibilidade de serviço
	@GetMapping("/off")
	public ResponseEntity<List<String>> getByIndisponibilidade() {
		return ResponseEntity.ok(service.findByIndisponibilidade());
	}

	// GET dos status dos serviços por estado filtrando por data
	@GetMapping("/data/{historicoTempo}")
	public ResponseEntity<List<ServicosNF>> getByHistoricoTempo(@PathVariable String historicoTempo) {
		return ResponseEntity.ok(service.findByHistoricoTempo(historicoTempo));
	}
}
