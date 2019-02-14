import { Component, OnInit } from '@angular/core';

import {SelectOptions} from '../components/select/select-options';

import {FormGroup, FormBuilder, Validators, AbstractControl} from '@angular/forms'
import {Router} from '@angular/router'

import {MembroService} from './membros.service';
import {MembroModel} from './modelo/membro.model';
import { MessageService } from 'primeng/components/common/messageservice';
import { MembroDTO } from './modelo/membro.dto';


@Component({
  selector: 'app-membros',
  templateUrl: './membros.component.html'
})
export class MembrosComponent implements OnInit {

  
  orderForm: FormGroup
  membros: MembroModel[]

  constructor(
    private router: Router,
    private membroService: MembroService,
    private formBuilder: FormBuilder,
    private messageService: MessageService) {
  }

  ngOnInit() {

   this.membros = new Array();
   this.membros[0]= new MembroModel(new Uint8Array(3),true,'Teste','asdf','asdfasdf','',new Date(),new Date(),null,'adf','asdf','ATIVO');
   this.membros[1]= new MembroModel(new Uint8Array(3),true,'Teste','asdf','asdfasdf','',new Date(),new Date(),null,'adf','asdf','ATIVO');
   this.membros[2]= new MembroModel(new Uint8Array(3),true,'Teste','asdf','asdfasdf','',new Date(),new Date(),null,'adf','asdf','LICENCA');
   this.membros[3]= new MembroModel(new Uint8Array(3),true,'Teste','asdf','asdfasdf','',new Date(),new Date(),null,'adf','asdf','DESLIGADO');
   this.membros[4]= new MembroModel(new Uint8Array(3),false,'Teste','asdf','asdfasdf','',new Date(),new Date(),null,'adf','asdf','DESLIGADO');

   console.log(this.membros);
  }

  
  
}
