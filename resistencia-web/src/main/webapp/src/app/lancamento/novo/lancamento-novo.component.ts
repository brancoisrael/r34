import { Component, OnInit, Input } from '@angular/core';

import {FormGroup, FormBuilder, Validators, AbstractControl} from '@angular/forms'
import {Router, ActivatedRoute} from '@angular/router'

import { MessageService } from 'primeng/components/common/messageservice';


@Component({
  selector: 'app-lancamento-novo',
  templateUrl: './lancamento-novo.component.html'
})
export class LancamentoNovoComponent implements OnInit {

  header:string='Cadastrar novo membro'
  orderForm: FormGroup
  
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder,
    private messageService: MessageService) {
      
  }

  ngOnInit() {
     
  }

  
}
