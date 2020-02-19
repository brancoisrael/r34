import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders }    from '@angular/common/http';
import {Http, Headers, RequestOptions} from '@angular/http'
import 'rxjs/add/operator/map'

import {R34_API} from '../app.api'
import {MembroModel} from './modelo/membro.model'
import { MembroDTO } from './modelo/membro.dto';
import { LoginDTO } from './modelo/login.dto';
import { TokenDTO } from './modelo/token.dto';

@Injectable()
export class MembroService{

    headers: Headers;
    
    constructor(private http:Http){}

    addHeaderrequest(){
        this.headers = new Headers()
        this.headers.append('content-type','application/json')
        this.headers.append('authorization',window.localStorage.getItem('token'));
    }

    salvarMembro(membro:MembroModel):Observable<MembroDTO>{       
        return this.http.post(`${R34_API}/membros/salvar`
        ,JSON.stringify(membro)
        ,new RequestOptions({headers:this.headers}))
            .map(response=> response.json())
    }

    atualizarMembro(membro:MembroModel):Observable<MembroDTO>{       
        this.addHeaderrequest()
        
        return this.http.post(`${R34_API}/membros/atualizar`
        ,JSON.stringify(membro)
        ,new RequestOptions({headers:this.headers}))
            .map(response=> response.json())
    }

    listarMembros():Observable<MembroModel[]>{       
        this.addHeaderrequest()
        return this.http.get(`${R34_API}/membros/buscartodos`
        ,new RequestOptions({headers:this.headers}))
            .map(response=> response.json())
    }

    
    buscarMembroID(id:number):Observable<MembroModel>{       
        this.addHeaderrequest()
        
        return this.http.get(`${R34_API}/membros/buscarid/${id}`
        ,new RequestOptions({headers:this.headers}))
            .map(response=> response.json())
            
    }

    buscarMembroStatus(status:boolean):Observable<MembroModel[]>{       
        this.addHeaderrequest()
        
        return this.http.get(`${R34_API}/membros/buscar-status/${status}`
        ,new RequestOptions({headers:this.headers}))
            .map(response=> response.json())
            
    }

    excluirMembro(membro:MembroModel):Observable<MembroDTO>{       
        this.addHeaderrequest()
        
        return this.http.delete(`${R34_API}/membros/excluir/${membro.id}`
        ,new RequestOptions({headers:this.headers}))
            .map(response=> response.json())
    }

    loginMembro(login:LoginDTO):Observable<TokenDTO>{
        this.addHeaderrequest()
        
        return this.http.post(`${R34_API}/acesso/login`
        ,JSON.stringify(login)
        ,new RequestOptions({headers:this.headers}))
            .map(response=> response.json())
    }
}