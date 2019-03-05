

export class ProdutoVendaModel{

    constructor(
        public id:number,
        public preco:number,
        public inicioVigencia:Date,
        public fimVigencia:Date,
        public produto:ProdutoVendaModel
    ){}
}