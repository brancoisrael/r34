import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders }    from '@angular/common/http';
import {Http, Headers, RequestOptions} from '@angular/http'
import 'rxjs/add/operator/map'

import {R34_API} from '../app.api'
import {MembroModel} from './membro.model'
import { MembroDTO } from './membro.dto';

@Injectable()
export class MembroService{

    constructor(private http:Http){}

    retorno :MembroDTO
    salvarMembro(membro:MembroModel):Observable<MembroDTO>{
        

        /*return this.http.post<MembroDTO>
        (`${R34_API}/membros/salvar`, membro);*/
        
        const headers = new Headers()
        headers.append('Content-Type','application/json')
        /*headers.append('Access-Control-Allow-Origin', '*')
        headers.append('Access-Control-Allow-Methods', 'GET,HEAD,OPTIONS,POST,PUT')
        headers.append('Access-Control-Allow-Header', 'Content-Type')*/
        
        return this.http.post(`${R34_API}/membros/salvar`
        ,JSON.stringify(membro)
        ,new RequestOptions({headers:headers}))
            .map(response=> response.json())
    }
}