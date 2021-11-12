import { NotaFiscal } from './../model/notaFiscal';
import { ServiceNFService } from './../service/service-nf.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-othertables',
  templateUrl: './othertables.component.html',
  styleUrls: ['./othertables.component.scss']
})
export class OthertablesComponent implements OnInit {

  // VARIAVEIS USADAS
  indisponibilidade: NotaFiscal
  listarDados: NotaFiscal[]
  estado: NotaFiscal

  uf: string
  data: string

  // IMPORTAÇÃO DOS SERVICES
  constructor(
    private service: ServiceNFService
  ) { }

  ngOnInit() {
    // CHAMANDO O END-POINT ASSIM QUE ATUALIZA A PAGE
    this.getIndisponibilidade()
  }

  // TODOS OS END-POITS
  getIndisponibilidade() {
    this.service.getByIndisponibilidade().subscribe((resp: NotaFiscal) => {
      this.indisponibilidade = resp
    })
  }

  getEstado() {
    this.service.getByEstado(this.uf).subscribe((resp: NotaFiscal[]) => {
      this.listarDados = resp
    })
  }

  getData() {

    this.service.getByHistoricoTempo(this.data).subscribe((resp: NotaFiscal[]) => {
      this.listarDados = resp
    })
  }

}
