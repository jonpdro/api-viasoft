import { NotaFiscal } from './../model/notaFiscal';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ServiceNFService {

  constructor(
    private http: HttpClient
  ) { }

  // DECLARANDO OS ENDPOINTS NO SERVICE PRINCIPAL
  getByStatus(): Observable<NotaFiscal[]> {
    return this.http.get<NotaFiscal[]>('http://localhost:8080/api/')
  }

  getByEstado(autorizador: string): Observable<NotaFiscal[]> {
    return this.http.get<NotaFiscal[]>(`http://localhost:8080/api/uf/${autorizador}`)
  }

  getByIndisponibilidade(): Observable<NotaFiscal> {
    return this.http.get<NotaFiscal>('http://localhost:8080/api/off')
  }

  getByHistoricoTempo(historicoTempo: string): Observable<NotaFiscal[]> {
    return this.http.get<NotaFiscal[]>(`http://localhost:8080/api/data/${historicoTempo}`)
  }
}
