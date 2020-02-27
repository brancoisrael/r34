alter TABLE core.tb_promocao add constraint uk_inicio_vigencia unique (inicio_vigencia,id_produto);
alter TABLE core.tb_promocao add constraint uk_fim_vigencia unique (fim_vigencia,id_produto);
alter TABLE core.tb_saldo_membro drop column saldo;
alter TABLE core.tb_saldo_membro add saldo double precision NOT NULL default 0;
alter table core.tb_saldo_membro ADD  FOREIGN KEY (id_membro) REFERENCES core.tb_membro (id) ON DELETE CASCADE;
CREATE UNIQUE INDEX CONCURRENTLY unique_produto_venda ON core.tb_produto_venda (fim_vigencia,id_produto);

insert into core.tb_tipo_produto (tipo_produto) values ('Cerveja');
insert into core.tb_tipo_produto (tipo_produto) values ('Comida');
insert into core.tb_tipo_produto (tipo_produto) values ('Refrigetante');

insert into core.tb_produto (descricao, habilitar_compra, habilitar_venda, id_tipo_produto) values('Kaiser',true,true,1);
insert into core.tb_produto (descricao, habilitar_compra, habilitar_venda, id_tipo_produto) values('Heineken',true,true,1);
insert into core.tb_produto (descricao, habilitar_compra, habilitar_venda, id_tipo_produto) values('Eisenbahn',false,false,1);
insert into core.tb_produto (descricao, habilitar_compra, habilitar_venda, id_tipo_produto) values('Coca cola',true,true,3);
insert into core.tb_produto (descricao, habilitar_compra, habilitar_venda, id_tipo_produto) values('Coca cola zero',true,true,3);

insert into core.tb_produto_venda (inicio_vigencia,preco,id_produto) values ('01/01/2020',4,1);
insert into core.tb_produto_venda (inicio_vigencia,preco,id_produto) values ('01/01/2020',4,2);
insert into core.tb_produto_venda (inicio_vigencia,preco,id_produto) values ('01/01/2020',4,3);
insert into core.tb_produto_venda (inicio_vigencia,preco,id_produto) values ('01/01/2020',5,4);
insert into core.tb_produto_venda (inicio_vigencia,preco,id_produto) values ('01/01/2020',6,5);

insert into core.tb_promocao (inicio_vigencia, fim_vigencia, quantidade_produto,valor,id_produto) values ('01/01/2020','12/31/2020',3,10,1);
insert into core.tb_promocao (inicio_vigencia, fim_vigencia, quantidade_produto,valor,id_produto) values ('01/01/2020','12/31/2020',3,10,2);
insert into core.tb_promocao (inicio_vigencia, fim_vigencia, quantidade_produto,valor,id_produto) values ('01/01/2020','12/31/2020',3,10,3);

