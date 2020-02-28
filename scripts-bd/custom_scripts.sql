create table core.tb_endpoint_regra_acesso(
	id_endpoint bigint NOT NULL,
	id_regra_acesso bigint NOT NULL,
	CONSTRAINT pk_endpoint_regraacesso PRIMARY KEY (id_endpoint,id_regra_acesso),
	CONSTRAINT fk01_endpoint FOREIGN KEY (id_endpoint) REFERENCES core.tb_endpoint (id),
	CONSTRAINT fk02_regra_acesso FOREIGN KEY (id_regra_acesso) REFERENCES core.tb_regra_acesso (id) on delete cascade
);

create table core.tb_membro_regra_acesso(
	id_regra_acesso bigint NOT NULL,
	id_membro bigint NOT NULL,
	CONSTRAINT pk_membro_regraacesso PRIMARY KEY (id_regra_acesso,id_membro),
	CONSTRAINT fk01_membro FOREIGN KEY (id_membro) REFERENCES core.tb_membro (id) on delete cascade,
	CONSTRAINT fk02_regra_acesso FOREIGN KEY (id_regra_acesso) REFERENCES core.tb_regra_acesso (id) on delete cascade
);

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

insert into core.tb_endpoint (url,descricao,left_menu) values ('/membros','Manutenção de membros do MC',true);
insert into core.tb_endpoint (url,descricao,left_menu) values ('/lancamento-novo','Manutenção de lançamentos de débito/crédito',true);

insert into core.tb_regra_acesso (rules_acesso) values (0);
insert into core.tb_regra_acesso (rules_acesso) values (1);
insert into core.tb_regra_acesso (rules_acesso) values (2);
insert into core.tb_regra_acesso (rules_acesso) values (3);
insert into core.tb_regra_acesso (rules_acesso) values (4);
insert into core.tb_regra_acesso (rules_acesso) values (5);

--perfil de desenvolvedor
insert into core.tb_endpoint_regra_acesso (id_endpoint, id_regra_acesso) values (1,1);
insert into core.tb_endpoint_regra_acesso (id_endpoint, id_regra_acesso) values (2,1);

--perfil de tesoureiro
insert into core.tb_endpoint_regra_acesso (id_endpoint, id_regra_acesso) values (2,2);

--perfil de secretario
insert into core.tb_endpoint_regra_acesso (id_endpoint, id_regra_acesso) values (1,3);

--inserir perfil membro
--insert into core.tb_membro_regra_acesso (id_membro,id_regra_acesso) values(5,2);


