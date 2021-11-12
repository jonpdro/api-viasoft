package br.com.apiviasoft.testApiViasoft.model;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

// Criação da tabela no banco de dados
@Entity
@Table(name = "servicosNF")
public class ServicosNF {

	// Declaração de todos os atributos da tabelas
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String autorizador;

	private String statusServico;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "GMT-3")
	private Date historicoTempo = new Date(System.currentTimeMillis());

	// Construtores padrão
	public ServicosNF() {
		super();
	}

	public ServicosNF(long id, String autorizador, String statusServico, Date historicoTempo) {
		super();
		this.id = id;
		this.autorizador = autorizador;
		this.statusServico = statusServico;
		this.historicoTempo = historicoTempo;
	}

	// Getters and Setters padrões
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAutorizador() {
		return autorizador;
	}

	public void setAutorizador(String autorizador) {
		this.autorizador = autorizador;
	}

	public String getStatusServico() {
		return statusServico;
	}

	public void setStatusServico(String statusServico) {
		this.statusServico = statusServico;
	}

	public Date getHistoricoTempo() {
		return historicoTempo;
	}

	public void setHistoricoTempo(Date historicoTempo) {
		this.historicoTempo = historicoTempo;
	}

}
