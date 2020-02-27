import { Component, OnInit } from '@angular/core';
import { LancamentoService } from 'app/lancamento/lancamento.service';
import { LancamentoModel, TIPO_LANCAMENTO, ORIGEM_DEBITO_LANCAMENTO, ORIGEM_CREDITO_LANCAMENTO } from 'app/lancamento/modelo/lancamento.model';
import { SaldoModel } from '../modelo/saldo.model';

@Component({
  selector: 'app-saldo',
  templateUrl: './saldo.component.html'
})
export class SaldoComponent implements OnInit {

  lancamentos:LancamentoModel[]
  saldoMembro : SaldoModel
  header:string='Extrato de lançamentos e saldo'

  constructor(
    private lancamentoService:LancamentoService
  ) {
    this.saldoMembro = new SaldoModel(0,0,null,null)
   }

  ngOnInit() {
    this.lancamentoService.lancamentoMembro(window.localStorage.getItem('email'))
    .subscribe((response:LancamentoModel[])=>{
      this.lancamentos=response;
     
      if(this.lancamentos!==null && this.lancamentos.length>0){
        this.saldoMembro = this.lancamentos[0].membro.saldoMembro;
      }

    })   
  }

  findTipoLancamento(key:string):string{
    return TIPO_LANCAMENTO.find(tp=>tp.value == key).label;
  }

  findOrigemLancamento(key:string):string{
    var ret = ORIGEM_DEBITO_LANCAMENTO.find(tp=>tp.value == key);
    if(!ret){
      ret = ORIGEM_CREDITO_LANCAMENTO.find(tp=>tp.value == key);
    }

    return ret.label;
  }
  

}
