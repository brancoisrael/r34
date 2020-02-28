import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import {Http, Headers, RequestOptions} from '@angular/http'
import 'rxjs/add/operator/map'

import {R34_API} from '../../app.api'
import { EndPointDTO } from '../../shared/dto/endpont.dto';

@Injectable()
export class SideBarService{

    headers: Headers;
    
    constructor(private http:Http){}

    addHeaderrequest(){
        this.headers = new Headers()
        this.headers.append('content-type','application/json')
        this.headers.append('authorization',window.sessionStorage.getItem('token'));
    }

    buscarMenuMembro():Observable<EndPointDTO[]>{       
        this.addHeaderrequest()
        return this.http.get(`${R34_API}/endpoint/buscar-menu-membro`
        ,new RequestOptions({headers:this.headers}))
            .map(response=> response.json())
    }

}