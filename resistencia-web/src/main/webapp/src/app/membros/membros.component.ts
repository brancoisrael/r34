import { Component, OnInit } from '@angular/core';

import {SelectOptions} from '../components/select/select-options';

import {FormGroup, FormBuilder, Validators, AbstractControl} from '@angular/forms'
import {Router} from '@angular/router'

import {MembroService} from './membros.service';
import {MembroModel, PATENTES, SITUACOES, CARGOS} from './modelo/membro.model';
import { MessageService } from 'primeng/components/common/messageservice';
import { MembroDTO } from './modelo/membro.dto';


@Component({
  selector: 'app-membros',
  templateUrl: './membros.component.html'
})
export class MembrosComponent implements OnInit {

  patentes=PATENTES
  situacoes=SITUACOES
  cargos=CARGOS
  
  orderForm: FormGroup
  membros: MembroModel[]

  constructor(
    private router: Router,
    private membroService: MembroService,
    private formBuilder: FormBuilder,
    private messageService: MessageService) {
  }

  ngOnInit() {
   this.membroService.listarMembros()
      .subscribe((response:MembroModel[])=>{
        this.membros=response
      })
      console.log(this.membros);
  }

  excluirMembro(membro:MembroModel){
    this.membroService.excluirMembro(membro)
      .subscribe((response:MembroDTO)=>{
        this.router.navigate(['/membros'])
        this.messageService.clear();
        this.messageService.add({severity:response.sucesso?'success':'error', summary:'Mensagem: ', detail:response.message}); 
     
       if(response.sucesso){
          const index = this.membros.indexOf(membro, 0);
          if(index!==-1)
            this.membros.splice(index, 1); 
        }
    })     
  }
  
}
