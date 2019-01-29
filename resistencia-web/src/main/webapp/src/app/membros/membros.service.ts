import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import {Http, Headers, RequestOptions} from '@angular/http'
import 'rxjs/add/operator/map'

import {R34_API} from '../app.api'
import {MembroModel} from './membro.model'

@Injectable()
export class MembroService{

    constructor(private http:Http){}

    salvarMembro(membro:MembroModel):Observable<string>{
        const headers = new Headers()
        headers.append('Content-Type','application/json')

        
        return this.http.post(`${R34_API}/membro/salvar`
        ,JSON.stringify(membro)
        ,new RequestOptions({headers:headers}))
            .map(response=> response.json())
            .map(membro=> membro.id)
    }
}