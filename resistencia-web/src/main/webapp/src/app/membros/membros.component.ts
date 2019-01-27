import { Component, OnInit } from '@angular/core';

import {SelectOptions} from '../components/select/select-options';

import {FormGroup, FormBuilder, Validators, AbstractControl} from '@angular/forms'
import {Router} from '@angular/router'

@Component({
  selector: 'app-membros',
  templateUrl: './membros.component.html'
})
export class MembrosComponent implements OnInit {

  orderForm: FormGroup

  mailPattern =/^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i
  numeroPattern =/^[0-9]*$/
  alfaPattern=/[A-Za-z]/

  patentes: SelectOptions[]=[
    {label:'Selecione',value:null},
    {label:'Rodando', value:'RODANDO'},
    {label:'PP', value:'PP'},
    {label:'Meio Escudo', value:'MEIO_EXCUDO'},
    {label:'Escudado', value:'ESCUDADO'}
  ];

  situacoes:SelectOptions[]=[
    {label:'Selecione',value:null},
    {value:'ATIVO', label:'Ativo'},
    {value:'LICENCA',label:'Em licencao'},
    {value:'DESLIGADO', label:'Desligado'}
  ]

  cargos:SelectOptions[]=[
    {label:'Selecione',value:null},
    {value:'PRESIDENTE',label:'Presidente'},
    {value:'VICE_PRESIDENTE',label:'Vice Presidente'},
    {value:'SARGENTO_ARMAS',label:'Sargento de armas'},
    {value:'SECRETARIO',label:'Secretário'},
    {value:'TESOUREIRO',label:'Tesoureiro'},
    {value:'SEM_CARGO',label:'Sem Cargo'}
  ]

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.orderForm = this.formBuilder.group({
      nome:this.formBuilder.control('',[Validators.required,Validators.max(50), Validators.pattern(this.alfaPattern)]),
      status:this.formBuilder.control('',Validators.required) ,
      apelido:this.formBuilder.control('') ,
      email:this.formBuilder.control('',Validators.required) ,
      emailConfirmacao:this.formBuilder.control('',Validators.required) ,
      senha:this.formBuilder.control('',Validators.required) ,
      dataNascimento:this.formBuilder.control('') ,
      dataEntrada:this.formBuilder.control('') , 
      dataSaida:this.formBuilder.control('') ,
      patente:this.formBuilder.control('',[Validators.required]),
      cargo:this.formBuilder.control('',Validators.required) ,
      situacao:this.formBuilder.control('',Validators.required) ,
    }),{validator:MembrosComponent.emailIgual}
  }

  static emailIgual(group:AbstractControl):{[key:string]:boolean}{
    const email = group.get('email')
    const emailConfirmacao = group.get('emailConfirmacao')

    if(!email || !emailConfirmacao){
      return undefined
    }

    if(email.value !== emailConfirmacao.value){
      return {emailsNotMatch:true};
    }

    return undefined

  }
}
