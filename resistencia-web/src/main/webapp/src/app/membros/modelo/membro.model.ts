import { SelectOptions } from "../../components/select/select-options";
import { SaldoModel } from "./saldo.model";

export class MembroModel{

    public cargoDescricao: string
    public patenteDescricao: string

    constructor( 
        public id: number,
        public status: boolean,
        public nome : string,
        public apelido: string,
        public email: string,
        public senha: string,
        public dataNascimento: Date,
        public dataEntrada: Date,
        public dataSaida: Date,
        public patente: string,
        public cargo: string ,
        public situacaoMembro: string,
        public saldoMembro:SaldoModel
    ){
        
    }
}

export const PATENTES: SelectOptions[]=[
    {label:'Selecione',value:null},
    {label:'Rodando', value:'RODANDO'},
    {label:'PP', value:'PP'},
    {label:'Meio Escudo', value:'MEIO_ESCUDO'},
    {label:'Escudado', value:'ESCUDADO'}
  ];

  export const SITUACOES:SelectOptions[]=[
    {label:'Selecione',value:null},
    {value:'ATIVO', label:'Ativo'},
    {value:'LICENCA',label:'Em licencao'},
    {value:'DESLIGADO', label:'Desligado'}
  ]

  export const CARGOS:SelectOptions[]=[
    {label:'Selecione',value:null},
    {value:'PRESIDENTE',label:'Presidente'},
    {value:'VICE_PRESIDENTE',label:'Vice Presidente'},
    {value:'SARGENTO_ARMAS',label:'Sargento de armas'},
    {value:'SECRETARIO',label:'Secretário'},
    {value:'TESOUREIRO',label:'Tesoureiro'},   
  ]