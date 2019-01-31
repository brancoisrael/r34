import { Component, OnInit } from '@angular/core';

import {SelectOptions} from '../components/select/select-options';

import {FormGroup, FormBuilder, Validators, AbstractControl} from '@angular/forms'
import {Router} from '@angular/router'

import {MembroService} from './membros.service';
import {MembroModel} from './membro.model';


@Component({
  selector: 'app-membros',
  templateUrl: './membros.component.html'
})
export class MembrosComponent implements OnInit {

  
  orderForm: FormGroup

  mailPattern =/^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i
  numeroPatterns =/^[0-9]*$/
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

  constructor(
    private router: Router,
    private membroService: MembroService,
    private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.orderForm = this.formBuilder.group({
      nome:this.formBuilder.control('',[Validators.required,Validators.max(50), Validators.pattern(this.alfaPattern)]),
      status:this.formBuilder.control('true',Validators.required) ,
      apelido:this.formBuilder.control('') ,
      email:this.formBuilder.control('',Validators.required) ,
      senha:this.formBuilder.control('',Validators.required) ,
      dataNascimento:this.formBuilder.control('') ,
      dataEntrada:this.formBuilder.control('') , 
      dataSaida:this.formBuilder.control('') ,
      patente:this.formBuilder.control('',[Validators.required]),
      cargo:this.formBuilder.control('',Validators.required) ,
      situacao:this.formBuilder.control('')
    })
  }

  salvarMembro(membro:MembroModel){
   
    this.membroService.salvarMembro(membro)
      .subscribe((membroId:string)=>{
        this.router.navigate(['/order-summary'])
        membro = null
      })
  }

  
}
