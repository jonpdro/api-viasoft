package br.com.apiviasoft.testApiViasoft.webscraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.com.apiviasoft.testApiViasoft.model.ServicosNF;

public class WebScrapping {

	// Método do que vai executar todo o Web Scrapping
	public static List<ServicosNF> getTableHTML() throws IOException {

		// Declarando os acessos ao URL do site
		Document doc = Jsoup.connect("http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx").get();

		Element table = doc.getElementsByClass("tabelaListagemDados").first();

		Elements tbody = table.getElementsByClass("linhaParCentralizada");
		
		Elements tbody2 = table.getElementsByClass("linhaImparCentralizada");
		
		List<Element> tbody3 = new ArrayList<>();
		tbody3.addAll(tbody);
		tbody3.addAll(tbody2);
		
		List<ServicosNF> tabelaNotaFiscal = new ArrayList<>();

		// Processo de extração de dados tabela Par
		for (Element listagemDados : tbody3) {

			ServicosNF notaFiscal = new ServicosNF();

			if (listagemDados.getElementsByTag("td").size() != 0) {

				String autorizador = listagemDados.getElementsByTag("td").get(0).text();
				notaFiscal.setAutorizador(autorizador);

				String img = listagemDados.getElementsByTag("td").get(5).getElementsByTag("img").get(0).attr("src");

				if (img.equals("imagens/bola_verde_P.png")) {
					notaFiscal.setStatusServico("OK");

				} else {
					notaFiscal.setStatusServico("OFF");

				}
			}

			// Adicionar todos os dados extraidos na model
			tabelaNotaFiscal.add(notaFiscal);
		}

		return tabelaNotaFiscal;
	}
}
