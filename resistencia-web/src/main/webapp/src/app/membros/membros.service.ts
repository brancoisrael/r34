import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders }    from '@angular/common/http';
import {Http, Headers, RequestOptions} from '@angular/http'
import 'rxjs/add/operator/map'

import {R34_API} from '../app.api'
import {MembroModel} from './modelo/membro.model'
import { MembroDTO } from './modelo/membro.dto';

@Injectable()
export class MembroService{

    constructor(private http:Http){}

    salvarMembro(membro:MembroModel):Observable<MembroDTO>{       
        const headers = new Headers()
        headers.append('Content-Type','application/json')
        
        return this.http.post(`${R34_API}/membros/salvar`
        ,JSON.stringify(membro)
        ,new RequestOptions({headers:headers}))
            .map(response=> response.json())
    }

    listarMembros():Observable<MembroModel[]>{       
        const headers = new Headers()
        headers.append('Content-Type','application/json')
        
        return this.http.get(`${R34_API}/membros/buscartodos`
        ,new RequestOptions({headers:headers}))
            .map(response=> response.json())
    }

    excluirMembro(membro:MembroModel):Observable<MembroDTO>{       
        const headers = new Headers()
        headers.append('Content-Type','application/json')
        
        return this.http.delete(`${R34_API}/membros/excluir/${membro.id}`
        ,new RequestOptions({headers:headers}))
            .map(response=> response.json())
    }
}