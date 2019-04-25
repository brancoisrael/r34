import { Component, OnInit, Input } from '@angular/core';
import {FormGroup, FormBuilder, Validators, AbstractControl} from '@angular/forms'
import {Router} from '@angular/router'

import {MembroService} from './membros.service';
import {MembroModel, PATENTES, SITUACOES, CARGOS} from './modelo/membro.model';
import { MembroDTO } from './modelo/membro.dto';

import { MessageService } from 'primeng/components/common/messageservice';
import {ConfirmationService} from 'primeng/api';



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
  msg:string

  constructor(
    private confirmationService: ConfirmationService,
    private router: Router,
    private membroService: MembroService,
    private formBuilder: FormBuilder,
    private messageService: MessageService) {
  }

  ngOnInit() {
   this.membroService.listarMembros()
      .subscribe((response:MembroModel[])=>{
        this.membros=response
        
        for(let m of this.membros){
          m.patenteDescricao = (PATENTES.find(p=>p.value == m.patente).label);

          if( m.cargo)
            m.cargoDescricao = (CARGOS.find(c=>c.value == m.cargo).label);
        }
      })
      
      
  }

  confirmaExcluir(membro:MembroModel) {
    this.confirmationService.confirm({
      message: 'Deseja realmente excluir este infeliz?',
      header: 'Confirme',
      icon: 'pi pi-exclamation-triangle',
      acceptLabel:'Sim',
      rejectLabel:'Não',
      accept: () => {
          this.messageService.add({severity:'info', summary:'Confirmed', detail:'You have accepted'});this.membroService.excluirMembro(membro)
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
      },
      reject: () => {
          
      }
  });
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
