export class MembroModel{

    constructor( 
        public id: BigInteger,
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
        public situacao: string
    ){}
}