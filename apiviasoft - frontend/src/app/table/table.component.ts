import { NotaFiscal } from './../model/notaFiscal';
import { ServiceNFService } from './../service/service-nf.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})
export class TableComponent implements OnInit {

  // VARIAVEIS E OBJETOS
  listarDados: NotaFiscal[]

  constructor(
    private service: ServiceNFService
  ) { }

  ngOnInit() {
    // CHAMANDO O END-POINT ASSIM QUE ATUALIZA A PAGE
    this.getTabela()
  }

  // ENDPOINT SENDO CHAMADO NA TABELA PRINCIPAL
  getTabela() {
    this.service.getByStatus().subscribe((resp: NotaFiscal[]) => {
      this.listarDados = resp
    });
  }

}
