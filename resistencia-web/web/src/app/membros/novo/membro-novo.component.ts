import { Component, OnInit, Input } from '@angular/core';

import {FormGroup, FormBuilder, Validators} from '@angular/forms'
import {Router, ActivatedRoute} from '@angular/router'

import {CALENDAR_PT_BR} from '../../components/calendar/calendar-pt-br'
import {YEAR_RANGE} from '../../components/calendar/calendar-pt-br'
import {MembroService} from '../membros.service';
import {MembroModel, PATENTES, CARGOS, SITUACOES} from '../modelo/membro.model';
import { MessageService } from 'primeng/components/common/messageservice';
import { MembroDTO } from '../modelo/membro.dto';



@Component({
  selector: 'app-membro-novo',
  templateUrl: './membro-novo.component.html'
})
export class MembroNovoComponent implements OnInit {

  msg:string
  header:string='Cadastrar novo membro'
  membro:MembroModel
  orderForm: FormGroup
  visibleBtNovo:boolean=true
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
  ptbr=CALENDAR_PT_BR;
  yearRange=YEAR_RANGE;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private membroService: MembroService,
    private formBuilder: FormBuilder,
    private messageService: MessageService) {
      this.minDateNascimento.setFullYear(1900);
     }

  ngOnInit() {
    this.orderForm = this.formBuilder.group({
      nome:this.formBuilder.control('',[Validators.required,Validators.max(50), Validators.pattern(this.alfaPattern)]),
      apelido:this.formBuilder.control('') ,
      email:this.formBuilder.control('',[Validators.required,Validators.pattern(this.mailPattern)]) ,
      senha:this.formBuilder.control('',Validators.required) ,
      dataNascimento:this.formBuilder.control('') ,
      dataEntrada:this.formBuilder.control('',Validators.required) , 
      dataSaida:this.formBuilder.control('') ,
      patente:this.formBuilder.control('',[Validators.required]),
      cargo:this.formBuilder.control('') ,
      situacaoMembro:this.formBuilder.control('',Validators.required),
      id:this.formBuilder.control('') ,
    })

    if(this.route.snapshot.params.id)
    this.prepararEditar(this.route.snapshot.params.id) 
  }

  changePatente(event){
    this.patenteDisable=true;
    this.orderForm.get('cargo').setValue(null);

    if(this.orderForm.get('patente').value ==='ESCUDADO'){
      this.patenteDisable=false;
    }
  }

  changeSituacao(event){
    this.dataSaidaDisable=true;
    if(this.orderForm.get('situacaoMembro').value ==='DESLIGADO'){
      this.dataSaidaDisable=false;
    }
  }

  setMinDateEntrada(event){
    this.minDateEntrada = this.orderForm.get('dataNascimento').value;
    this.orderForm.get('dataEntrada').setValue(null);
  }

  setMinDateSaida(event){
    this.minDateSaida = this.orderForm.get('dataEntrada').value;
    this.orderForm.get('dataSaida').setValue(null);
  }

  prepararEditar(id:number){
    this.header = 'Atualizar membro';
    this.visibleBtNovo=false;
    this.membroService.buscarMembroID(id)
    .subscribe((membro:MembroModel)=>{
     this.orderForm.controls['dataNascimento'].setValue(new Date(membro.dataNascimento));
     this.orderForm.controls['dataEntrada'].setValue(new Date(membro.dataEntrada));
     this.orderForm.controls['dataSaida'].setValue(membro.situacaoMembro==='DESATIVADO'? new Date(membro.dataSaida):null);
     this.orderForm.controls['situacaoMembro'].setValue(membro.situacaoMembro);
     this.orderForm.controls['patente'].setValue(membro.patente);
     this.orderForm.controls['cargo'].setValue(membro.cargo);
     this.orderForm.controls['nome'].setValue(membro.nome);
     this.orderForm.controls['apelido'].setValue(membro.apelido);
     this.orderForm.controls['senha'].setValue(membro.senha);
     this.orderForm.controls['email'].setValue(membro.email);
     this.orderForm.controls['id'].setValue(membro.id);   
     
     this.changeSituacao(null);
     this.patenteDisable=this.orderForm.get('patente').value ==='ESCUDADO'?false:true;
     this.minDateEntrada = this.orderForm.get('dataNascimento').value;
     this.minDateSaida = this.orderForm.get('dataEntrada').value;
     
    })
  }

  salvarMembro(membro:MembroModel){
    membro.status=membro.situacaoMembro==='DESLIGADO'?false:true;

    this.membroService.salvarMembro(membro)
      .subscribe((response:MembroDTO)=>{
        this.router.navigate(['/membro-novo'])
        this.messageService.clear();
        this.messageService.add({severity:response.sucesso?'success':'error', summary:'Mensagem: ', detail:response.message}); 
        
        if(response.sucesso){
          this.orderForm.reset();
        }
      })     
  }

  atualizarMembro(membro:MembroModel){
    membro.status=membro.situacaoMembro==='DESLIGADO'?false:true;

    this.membroService.atualizarMembro(membro)
      .subscribe((response:MembroDTO)=>{
        this.messageService.clear();
        this.messageService.add({severity:response.sucesso?'success':'error', summary:'Mensagem: ', detail:response.message}); 
        
        if(response.sucesso){
          this.router.navigate(['/membros'])        
        }
      })     
  }
}
