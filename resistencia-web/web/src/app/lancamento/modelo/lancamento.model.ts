import { SelectOptions } from "../../components/select/select-options";
import { MembroModel } from "../../membros/modelo/membro.model";
import { ProdutoVendaModel } from "../../produto/modelo/produto-venda.model"
import { ProdutoModel } from "../../produto/modelo/produto.model";

export class LancamentoModel{

    constructor(
      public id:number,
      public dataLancamento:Date,
      public criadoEm:Date,
      public valorLancamento:number,
      public quantidade:number,
      public tipoLancamento:string,
      public statusLancamento:string,
      public origemLancamento:string,
      public lancamento:string,
      public membro:MembroModel,
      public responsavelLancamento:MembroModel,
      public produtoVenda:ProdutoVendaModel      
    ){
    }
}

export const TIPO_LANCAMENTO: SelectOptions[]=[
    {label:'Selecione',value:null},
    {label:'Débito', value:'DEBITO'},
    {label:'Crédito', value:'CREDITO'}
  ];

export const STATUS_LANCAMENTO: SelectOptions[]=[
   {label:'Selecione',value:null},
   {label:'Aguardando quitação', value:'AGUARDANDO_QUITACAO'},
   {label:'Quitado', value:'QUITADO'},
   {label:'Em atraso', value:'EM_ATRASO'}
];

export const ORIGEM_DEBITO_LANCAMENTO: SelectOptions[]=[
    {label:'Selecione',value:null},
    {label:'Bar', value:'BAR'},
    {label:'Caixinha', value:'CAIXINHA'},
    {label:'Multa por mensagem no WhatsApp', value:'MULTA_WHATSAPP'},
    {label:'Multa de atraso de pagamento de caixinha', value:'MULTA_ATRASO_CAIXINHA'}
 ];

 export const ORIGEM_CREDITO_LANCAMENTO: SelectOptions[]=[
  {label:'Selecione',value:null},
  {label:'Aquisição de bens', value:'AQUISICAO_BENS'},
  {label:'Aquisição de serviços', value:'AQUISICAO_SERVICO'},
  {label:'Quitação de débitos', value:'QUITACAO_DEBITOS'}
];