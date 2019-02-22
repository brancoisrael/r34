import { Component, OnInit, Input } from '@angular/core';

import {SelectOptions} from '../../components/select/select-options';

import {FormGroup, FormBuilder, Validators, AbstractControl} from '@angular/forms'
import {Router, ActivatedRoute} from '@angular/router'

import {MembroService} from '../membros.service';
import {MembroModel, PATENTES, CARGOS, SITUACOES} from '../modelo/membro.model';
import { MessageService } from 'primeng/components/common/messageservice';
import { MembroDTO } from '../modelo/membro.dto';


@Component({
  selector: 'app-membro-novo',
  templateUrl: './membro-novo.component.html'
})
export class MembroNovoComponent implements OnInit {

  membro:MembroModel
  orderForm: FormGroup
  dataSaidaDisable:boolean=true
  patenteDisable:boolean=true
  minDateNascimento:Date=new Date()
  minDateEntrada:Date
  minDateSaida:Date
  patentes=PATENTES
  situacoes=SITUACOES
  cargos=CARGOS
  mailPattern =/^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i
  numeroPattern =/^[0-9]*$/
  alfaPattern=/[A-Za-z]/

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private membroService: MembroService,
    private formBuilder: FormBuilder,
    private messageService: MessageService) {
      this.minDateNascimento.setFullYear(1900);
     }

  ngOnInit() {
    if(this.route.snapshot.params.id)
      this.prepararEditar(this.route.snapshot.params.id) 

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
      cargo:this.formBuilder.control('') ,
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
        this.router.navigate(['/membro-novo'])
        //console.log(response);
        this.messageService.clear();
        this.messageService.add({severity:response.sucesso?'success':'error', summary:'Mensagem: ', detail:response.message}); 
        
        if(response.sucesso){
          this.orderForm.reset();
          this.orderForm.get('status').setValue(true);
        }
      })     
  }

  prepararEditar(id:number){
    this.membroService.buscarMembroID(id)
    .subscribe((membro:MembroModel)=>{
      this.orderForm.get('nome').setValue(membro.nome);
      this.orderForm.get('status').setValue(membro.status);
      this.orderForm.get('apelido').setValue(membro.apelido);
      this.orderForm.get('senha').setValue(membro.senha);
      this.orderForm.get('email').setValue(membro.email);
      this.orderForm.get('dataNascimento').setValue(membro.dataNascimento);
      this.orderForm.get('dataEntrada').setValue(membro.dataEntrada);
      this.orderForm.get('dataSaida').setValue(membro.dataSaida);
      this.orderForm.get('patente').setValue(membro.patente);
      //this.orderForm.get('situacaoMembro').setValue(membro.situacaoMembro);
      this.orderForm.controls.[''].u

     // this.orderForm.updateValueAndValidity({onlySelf: true})
    })    
  }
}
