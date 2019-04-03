import { LancamentoModel } from "./lancamento.model";

export class LancamentoDTO{

    constructor(        
        public sucesso: boolean,
        public message : string,
        public lancamento: LancamentoModel[]
    ){}
}