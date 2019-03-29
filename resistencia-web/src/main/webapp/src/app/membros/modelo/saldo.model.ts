import { MembroModel } from "./membro.model";

export class SaldoModel{

    constructor( 
        public id: number,
        public saldo:number,
        public ultimaAtualizacao:Date,
        public membro:MembroModel
    ){        
    }
}
