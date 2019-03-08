import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders }    from '@angular/common/http';
import {Http, Headers, RequestOptions} from '@angular/http'
import 'rxjs/add/operator/map'

import {R34_API} from '../app.api'
import { LancamentoModel } from './modelo/lancamento.model';
import { LancamentoDTO } from './modelo/lancamento.dto';

@Injectable()
export class LancamentoService{

    constructor(private http:Http){}

    salvarLancamento(lancamento:LancamentoModel):Observable<LancamentoDTO>{       
        const headers = new Headers()
        headers.append('Content-Type','application/json')
        
        return this.http.post(`${R34_API}/lancamentos/salvar`
        ,JSON.stringify(lancamento)
        ,new RequestOptions({headers:headers}))
            .map(response=> response.json())
    }

    listarLancamento(idMembro:number):Observable<LancamentoModel[]>{       
        const headers = new Headers()
        headers.append('Content-Type','application/json')
        
        return this.http.get(`${R34_API}/lancamentos/pesquisar-membro/${idMembro}`
        ,new RequestOptions({headers:headers}))
            .map(response=> response.json())
    }

}