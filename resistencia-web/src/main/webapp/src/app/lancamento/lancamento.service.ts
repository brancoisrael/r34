import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders }    from '@angular/common/http';
import {Http, Headers, RequestOptions} from '@angular/http'
import 'rxjs/add/operator/map'

import {R34_API} from '../app.api'

@Injectable()
export class LancamentoService{

    constructor(private http:Http){}

    /*salvarMembro(membro:MembroModel):Observable<MembroDTO>{       
        const headers = new Headers()
        headers.append('Content-Type','application/json')
        
        return this.http.post(`${R34_API}/membros/salvar`
        ,JSON.stringify(membro)
        ,new RequestOptions({headers:headers}))
            .map(response=> response.json())
    }*/

}