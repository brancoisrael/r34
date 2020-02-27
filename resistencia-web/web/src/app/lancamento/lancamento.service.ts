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

    private headers: Headers;

    constructor(private http:Http){}

    addHeaderrequest(){
        this.headers = new Headers()
        this.headers.append('Content-Type','application/json')
        this.headers.append('authorization',window.localStorage.getItem('token'));
    }

    salvarLancamento(lancamento:LancamentoModel):Observable<LancamentoDTO>{       
       this.addHeaderrequest();
        
        return this.http.post(`${R34_API}/lancamentos/salvar`
        ,JSON.stringify(lancamento)
        ,new RequestOptions({headers:this.headers}))
            .map(response=> response.json())
    }

    excluirLancamento(lancamento:LancamentoModel):Observable<LancamentoDTO>{       
        this.addHeaderrequest();
        
        return this.http.post(`${R34_API}/lancamentos/excluir`
        ,JSON.stringify(lancamento)
        ,new RequestOptions({headers:this.headers}))
            .map(response=> response.json())
    }

    listarLancamento(idMembro:number):Observable<LancamentoModel[]>{       
        this.addHeaderrequest();
        
        return this.http.get(`${R34_API}/lancamentos/pesquisar-membro/${idMembro}`
        ,new RequestOptions({headers:this.headers}))
            .map(response=> response.json())
    }

}