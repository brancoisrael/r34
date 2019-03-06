import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders }    from '@angular/common/http';
import {Http, Headers, RequestOptions} from '@angular/http'
import 'rxjs/add/operator/map'

import {TipoProdutoModel} from "../produto/modelo/tipo-produto.model"
import {ProdutoModel} from "../produto/modelo/produto.model"

import {R34_API} from '../app.api'

@Injectable()
export class ProdutoService{

    constructor(private http:Http){}

    listarTipoProduto():Observable<TipoProdutoModel[]>{       
        const headers = new Headers()
        headers.append('Content-Type','application/json')
        
        return this.http.get(`${R34_API}/produtos/listar-tipo-produto`
        ,new RequestOptions({headers:headers}))
            .map(response=> response.json())
    }

    listarProdutosVenda(idTipoProduto:number):Observable<ProdutoModel[]>{       
        const headers = new Headers()
        headers.append('Content-Type','application/json')
        
        return this.http.get(`${R34_API}/produtos/listar-produtos-vendas/${idTipoProduto}`
        ,new RequestOptions({headers:headers}))
            .map(response=> response.json())
            
    }
}