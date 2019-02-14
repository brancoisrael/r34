import { Component, OnInit } from '@angular/core';

import {SelectOptions} from '../../components/select/select-options';

import {FormGroup, FormBuilder, Validators, AbstractControl} from '@angular/forms'
import {Router} from '@angular/router'

import {MembroService} from '../membros.service';
import {MembroModel} from '../modelo/membro.model';
import { MessageService } from 'primeng/components/common/messageservice';
import { MembroDTO } from '../modelo/membro.dto';


@Component({
  selector: 'app-membro-novo',
  templateUrl: './membro-novo.component.html'
})
export class MembroNovoComponent implements OnInit {

  
  orderForm: FormGroup
  dataSaidaDisable:boolean=true
  patenteDisable:boolean=true
  minDateNascimento:Date=new Date()
  minDateEntrada:Date
  minDateSaida:Date

  mailPattern =/^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i
  numeroPattern =/^[0-9]*$/
  alfaPattern=/[A-Za-z]/

  patentes: SelectOptions[]=[
    {label:'Selecione',value:null},
    {label:'Rodando', value:'RODANDO'},
    {label:'PP', value:'PP'},
    {label:'Meio Escudo', value:'MEIO_ESCUDO'},
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
   
  ]

  constructor(
    private router: Router,
    private membroService: MembroService,
    private formBuilder: FormBuilder,
    private messageService: MessageService) {
      this.minDateNascimento.setFullYear(1900);
     }

  ngOnInit() {
    this.orderForm = this.formBuilder.group({
      nome:this.formBuilder.control('',[Validators.required,Validators.max(50), Validators.pattern(this.alfaPattern)]),
      status:this.formBuilder.control('true',Validators.required) ,
      apelido:this.formBuilder.control('') ,
      email:this.formBuilder.control('',[Validators.required,Validators.pattern(this.mailPattern)]) ,
      senha:this.formBuilder.control('',Validators.required) ,
      dataNascimento:this.formBuilder.control('') ,
      dataEntrada:this.formBuilder.control('',Validators.required) , 
      dataSaida:this.formBuilder.control('') ,
      patente:this.formBuilder.control('',[Validators.required]),
      cargo:this.formBuilder.control('',Validators.required) ,
      situacaoMembro:this.formBuilder.control('',Validators.required)
    })
  }

  changePatente(event){
    this.patenteDisable=true;
    this.orderForm.get('cargo').setValue(null);

    if(this.orderForm.get('patente').value ==='ESCUDADO'){
      this.patenteDisable=false;
    }
  }

  changeDataSaida(event){
    this.dataSaidaDisable=true;

    if(this.orderForm.get('situacaoMembro').value ==='DESLIGADO'){
      this.dataSaidaDisable=false;
    }
  }

  setMinDateEntrada(event){
    this.minDateEntrada = this.orderForm.get('dataNascimento').value;
    this.orderForm.get('dataEntrada'). setValue(null);
  }

  setMinDateSaida(event){
    this.minDateSaida = this.orderForm.get('dataEntrada').value;
    this.orderForm.get('dataSaida'). setValue(null);
  }

  salvarMembro(membro:MembroModel){
   
    this.membroService.salvarMembro(membro)
      .subscribe((response:MembroDTO)=>{
        this.router.navigate(['/membros'])
        //console.log(response);
        this.messageService.clear();
        this.messageService.add({severity:response.sucesso?'success':'error', summary:'Mensagem: ', detail:response.message}); 
        
        if(response.sucesso){
          this.orderForm.reset();
          this.orderForm.get('status').setValue(true);
        }
      })     
  }

  
}
