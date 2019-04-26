import { LancamentoModel } from "./lancamento.model";
import { SaldoModel } from "../../membros/modelo/saldo.model";

export class LancamentoDTO{

    constructor(        
        public sucesso: boolean,
        public message : string,
        public lancamentos: LancamentoModel[],
        public saldoMembro: SaldoModel
    ){}
}