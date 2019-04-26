
export class ProdutoModel{

    constructor(
        public id:number,
        public descricao:string,
        public habilitarCompra:boolean,
        public habilitarVenda:boolean
    ){}
}