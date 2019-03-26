import { Component, OnInit, Input } from '@angular/core';

import {FormGroup, FormBuilder, Validators, AbstractControl} from '@angular/forms'
import {Router, ActivatedRoute} from '@angular/router'

import { MessageService } from 'primeng/components/common/messageservice';
import {ProdutoService} from '../../produto/produto.service';
import {LancamentoService} from '../../lancamento/lancamento.service';
import { MembroService } from '../../membros/membros.service';
import {TIPO_LANCAMENTO,ORIGEM_DEBITO_LANCAMENTO,ORIGEM_CREDITO_LANCAMENTO,STATUS_LANCAMENTO, LancamentoModel} from '../../lancamento/modelo/lancamento.model'

import {TipoProdutoModel} from '../../produto/modelo/tipo-produto.model';
import {ProdutoModel} from '../../produto/modelo/produto.model';
import {ProdutoVendaModel} from '../../produto/modelo/produto-venda.model';

import { SelectOptions } from '../../components/select/select-options';
import { MembroModel } from '../../membros/modelo/membro.model';
import { ProdutoVendaDTO } from '../../produto/modelo/produto-venda.dto';
import { LancamentoDTO } from '../modelo/lancamento.dto';



@Component({
  selector: 'app-lancamento-novo',
  templateUrl: './lancamento-novo.component.html'
})
export class LancamentoNovoComponent implements OnInit {

  produtoVenda:ProdutoVendaModel
  tiposProdutos:SelectOptions[]
  produtos:SelectOptions[]
  membros:SelectOptions[]
  tiposLancamentos=TIPO_LANCAMENTO
  lancamentos:LancamentoModel[]
  origemLancamentos:any
  produtoDisable:boolean
  formHidden:boolean
  header:string='Cadastrar novo lançamento'
  orderForm: FormGroup
  numeroPattern =/^[0-9]*$/
  moedaPattern =/^[0-9,.]*$/
  dataHoje:Date=new Date()
 
  constructor(
    private lancamentoService:LancamentoService,
    private produtoService:ProdutoService,
    private membroService:MembroService,
    private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder,
    private messageService: MessageService) {
      this.produtoDisable=true;
      this.formHidden=true
  }

  ngOnInit() {
    this.orderForm = this.formBuilder.group({
      dataLancamento:this.formBuilder.control('',Validators.required),
      membro:this.formBuilder.control('',[Validators.required]),
      tipoProduto:this.formBuilder.control(''),
      produto:this.formBuilder.control(''),
      tipoLancamento:this.formBuilder.control(''),
      origemLancamento:this.formBuilder.control('',[Validators.required]),
      quantidade:this.formBuilder.control('',[Validators.required,Validators.pattern(this.numeroPattern)]),
      valorLancamento:this.formBuilder.control('',[Validators.required,Validators.pattern(this.moedaPattern)])
    })
    this.listarMembros();
  }

  listarMembros(){
    this.membroService.buscarMembroStatus(true)
      .subscribe((response:MembroModel[])=>{
        var mb:MembroModel[]=response
        this.membros = [{label:'Selecione',value:null}];
        
        for(var i=0;i<mb.length;i++){          
          this.membros.push(new SelectOptions(mb[i].nome,mb[i]));
        }
      })
  }

  hiddenForms(event){
    this.formHidden=this.orderForm.get('dataLancamento')?false:true;
  }

  changeTipoProduto(event){
    this.produtoVenda=null;
    this.orderForm.controls['produto'].setValue(null);
    this.produtos=null;

    this.produtoService.listarProdutosVenda(this.orderForm.controls['tipoProduto'].value)
      .subscribe((response:ProdutoModel[])=>{
        var pr:ProdutoModel[] = response;
        this.produtos = [{label:'Selecione',value:null}];
        for(var i=0;i<pr.length;i++){
          this.produtos.push(new SelectOptions(pr[i].descricao,pr[i].id))
        }
      })
  }

  changeTipoLancamento(event){
    this.produtoVenda=null;
    this.orderForm.controls['origemLancamento'].setValue(null);
    this.orderForm.controls['tipoProduto'].setValue(null);
    this.orderForm.controls['produto'].setValue(null);
    this.orderForm.controls['valorLancamento'].setValue(0);
    this.produtoDisable=true;
    this.origemLancamentos = null;
    
    if(this.orderForm.controls['tipoLancamento'].value==='CREDITO')
      this.origemLancamentos = ORIGEM_CREDITO_LANCAMENTO;
    else if(this.orderForm.controls['tipoLancamento'].value==='DEBITO')
      this.origemLancamentos = ORIGEM_DEBITO_LANCAMENTO;
  }

  changeOrigemLancamento(event){
    this.produtoVenda=null;
    this.produtoDisable=true;
    this.orderForm.controls['quantidade'].setValue(1);
    this.orderForm.controls['valorLancamento'].setValue(0);
    this.orderForm.controls['tipoProduto'].setValue(null);
    this.orderForm.controls['produto'].setValue(null);
   
    if(this.orderForm.controls['origemLancamento'].value==='BAR'){
      this.listarTipoProduto();
      this.produtoDisable=false;
    }      
  }

  listarTipoProduto(){
    this.produtoService.listarTipoProduto()
      .subscribe((response:TipoProdutoModel[])=>{
        var tp:TipoProdutoModel[]=response
        this.tiposProdutos = [{label:'Selecione',value:null}];
        
        for(var i=0;i<tp.length;i++){          
          this.tiposProdutos.push(new SelectOptions(tp[i].tipoProduto,tp[i].id));
        }
      })
  }

  calcPrecoVendaProduto(event){

    var dto:ProdutoVendaDTO=new ProdutoVendaDTO(this.orderForm.controls['produto'].value,this.orderForm.controls['dataLancamento'].value);
   
    this.produtoService.selectProdutoVendaByData(dto)
      .subscribe((response:ProdutoVendaModel)=>{
        this.produtoVenda=response;

        if(this.orderForm.controls['quantidade'].value===undefined)
          this.orderForm.controls['quantidade'].setValue(1);

        this.orderForm.controls['valorLancamento'].setValue(this.orderForm.controls['quantidade'].value*response.preco);
      })
  }

  salvarLancamento(lancamento:LancamentoModel){
    lancamento.produtoVenda=this.produtoVenda;

    this.lancamentoService.salvarLancamento(lancamento)
      .subscribe((response:LancamentoDTO)=>{
        this.messageService.clear();
        this.messageService.add({severity:response.sucesso?'success':'error', summary:'Mensagem: ', detail:response.message}); 

        if(response.sucesso){
          this.lancamentos.push(lancamento)
        }
      })           
  }

  listarLancamento(event){
    var m = this.orderForm.controls['membro'].value;
    this.lancamentos = null;
    if(m!==null && m!==undefined){
      this.lancamentoService.listarLancamento(m.id)
        .subscribe((response:LancamentoModel[])=>{
          this.lancamentos=response;
        })           
    }
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
