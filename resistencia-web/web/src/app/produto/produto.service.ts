import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders }    from '@angular/common/http';
import {Http, Headers, RequestOptions} from '@angular/http'
import 'rxjs/add/operator/map'

import {TipoProdutoModel} from "../produto/modelo/tipo-produto.model"
import {ProdutoModel} from "../produto/modelo/produto.model"
import {ProdutoVendaDTO} from "../produto/modelo/produto-venda.dto"

import {R34_API} from '../app.api'
import { ProdutoVendaModel } from './modelo/produto-venda.model';

@Injectable()
export class ProdutoService{

    private headers : Headers

    constructor(private http:Http){}

    addHeaderrequest(){
        this.headers = new Headers()
        this.headers.append('Content-Type','application/json')
        this.headers.append('authorization',window.localStorage.getItem('token'));
    }

    listarTipoProduto():Observable<TipoProdutoModel[]>{       
        this.addHeaderrequest();
       
        return this.http.get(`${R34_API}/produtos/listar-tipo-produto`
        ,new RequestOptions({headers:this.headers}))
            .map(response=> response.json())
    }

    listarProdutosVenda(idTipoProduto:number):Observable<ProdutoModel[]>{       
        this.addHeaderrequest();
       
        return this.http.get(`${R34_API}/produtos/listar-produtos-vendas/${idTipoProduto}`
        ,new RequestOptions({headers:this.headers}))
            .map(response=> response.json())
            
    }

    selectProdutoVendaByData(produtoVendaDTO:ProdutoVendaDTO):Observable<ProdutoVendaModel>{       
        this.addHeaderrequest();
        
        return this.http.post(`${R34_API}/produtos/buscar-produto-venda-data`
        ,JSON.stringify(produtoVendaDTO)
        ,new RequestOptions({headers:this.headers}))
            .map(response=> response.json())
            
    }

}