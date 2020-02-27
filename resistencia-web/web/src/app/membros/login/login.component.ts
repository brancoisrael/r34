import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { MembroService } from '../membros.service';
import { LoginDTO } from '../modelo/login.dto'
import { MessageService } from 'primeng/api';
import { TokenDTO } from '../modelo/token.dto';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {


  token:string
  msg:string
  orderForm: FormGroup;
  mailPattern =/^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private membroService: MembroService,
    private formBuilder: FormBuilder,
    private messageService: MessageService) { 
      this.messageService.add({severity:'success', summary:'Mensagem: ', detail:'teste'}); 
      
        
    }

    ngOnInit() {
      this.orderForm = this.formBuilder.group({
        email:this.formBuilder.control('',[Validators.required,Validators.pattern(this.mailPattern)]) ,
        senha:this.formBuilder.control('',Validators.required)         
      })
    }

    login(loginDTO:LoginDTO){
       
      this.membroService.loginMembro(loginDTO)
        .subscribe((response:TokenDTO)=>{

          this.messageService.clear();
          if(!response.token){
             this.messageService.add({severity:'error', summary:'Mensagem: ', detail:'Credenciais inválidas'}); 
             return;
          }

          window.localStorage.setItem('token',response.token);
          console.log(window.localStorage.getItem('token'))     
          this.router.navigate(['/membros'])  
        })     
    }

    getTitle(){
      return 'Login';
    }
}
