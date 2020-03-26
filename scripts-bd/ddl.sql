create schema core;

drop table core.tb_template_lancamento;
drop table core.tb_saldo_membro;
drop table core.tb_membro_regra_acesso;
drop table core.tb_endpoint_regra_acesso;
drop table core.tb_lancamento_promocao;
drop table core.tb_lancamento;
drop table core.tb_promocao;
drop table core.tb_produto_venda;
drop table core.tb_endpoint;
drop table core.tb_membro;
drop table core.tb_regra_acesso;
drop table core.tb_produto;
drop table core.tb_tipo_produto;


create table core.tb_endpoint(
 id serial primary key,
 descricao varchar(255) ,
 left_menu boolean,
 url varchar(255) not null
);

create table core.tb_regra_acesso(
 id serial primary key,
 rules_acesso integer not null
);

create table core.tb_endpoint_regra_acesso(
 id_endpoint integer not null,
 id_regra_acesso integer not null,
 constraint fk_endpoint foreign key (id_endpoint) REFERENCES core.tb_endpoint (id),
 constraint fk_regra_acesso foreign key (id_regra_acesso) REFERENCES core.tb_regra_acesso (id)
);

alter table core.tb_endpoint_regra_acesso add unique (id_endpoint,id_regra_acesso);


create table core.tb_membro(
 id serial primary key,
 status boolean not null,
 nome varchar(50) not null, 
 apelido varchar(15),
 email varchar(50) not null,
 senha varchar(30) not null,
 situacao_membro integer not null,
 data_nascimento date not null,
 data_entrada date not null,
 data_saida date,
 patente integer not null,
 cargo integer 
);

create table core.tb_membro_regra_acesso(
 id_regra_acesso serial primary key,
 id_membro integer not null,
 constraint fk_regra_acesso foreign key (id_regra_acesso) references core.tb_regra_acesso (id),
 constraint fk_membro foreign key (id_membro) references core.tb_membro (id)
);

alter table core.tb_membro_regra_acesso add unique  (id_regra_acesso,id_membro);

create table core.tb_tipo_produto(
 id serial primary key,
 tipo_produto varchar(20) not null
);

create table core.tb_produto(
 id serial primary key,
 descricao varchar(255) not null,
 habilitar_compra boolean not null,
 habilitar_venda boolean not null,
 id_tipo_produto integer not null,
 constraint fk_tipo_produto foreign key (id_tipo_produto) references core.tb_tipo_produto (id) on delete no action
);

create table core_tb_produto_venda(
 id serial primary key,
 preco decimal(10,2) not null,
 inicio_vigencia date not null,
 fim_vigencia date,
 id_produto integer not null ,
 constraint fk_produto foreign key (id_produto) references core.tb_produto (id) on delete no action
);

create table core.tb_saldo_membro(
 id serial primary key,
 saldo money not null,
 ultima_atualizacoa date not null,
 id_membro integer not null,
 constraint fk_membro foreign key (id_membro) references core.tb_membro (id) on delete cascade
);

create table core.tb_template_lancamento(
 id serial primary key,
 recorrente boolean not null,
 valor_lancamento money not null,
 tipo_lancamento integer not null,
 status_lancamento integer not null,
 origem_lancamento integer not null,
 id_membro integer not null,
 id_responsavel_lancamento integer not null,
 constraint fk_membro foreign key (id_membro) references core.tb_membro (id) on delete cascade,
 constraint fk_membro_responsael foreign key (id_responsavel_lancamento) references core.tb_membro (id) on delete no action
);

create table core.tb_promocao(
 id serial primary key,
 quantidade integer not null,
 valor money not null,
 inicio_vigencia date not null,
 fim_vigencia date not null,
 id_produto integer not null,
 constraint fk_produto foreign key (id_produto) references core.tb_produto (id) on delete no action
);

create table core.tb_produto_venda(
 id serial primary key,
 preco money not null,
 inicio_vigencia date not null,
 fim_vigencia date,
 id_produto integer not null,
 constraint fk_produto foreign key (id_produto) references core.tb_produto (id) on delete cascade
);

create table core.tb_lancamento(
 id serial primary key,
 data_lancamento date not null,
 criado_em date not null,
 valor_lancamento money not null,
 quantidade integer not null,
 observacao varchar(2000),
 tipo_lancamento integer not null,
 status_lancamento integer not null,
 origem_lancamento integer not null,
 id_membro integer not null,
 resposavel_lancamento integer not null,
 id_produto_venda integer not null,
 constraint fk_membro foreign key (id_membro) references core.tb_membro (id) on delete no action,
 constraint fk_membro_responsavel foreign key (resposavel_lancamento) references core.tb_membro (id) on delete no action,
 constraint fk_produto foreign key (id_produto_venda) references core.tb_produto_venda (id) on delete no action
);

create table core.tb_lancamento_promocao(
 id_lancamento serial not null primary key,
 id_promocao integer not null,
 constraint fk_lancamento foreign key (id_lancamento) references core.tb_lancamento (id) on delete cascade,
 constraint fk_promocao foreign key (id_promocao) references core.tb_promocao (id) on delete no action
);

alter table core.tb_lancamento_promocao add unique  (id_lancamento,id_promocao);

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

insert into core.tb_promocao (inicio_vigencia, fim_vigencia, quantidade,valor,id_produto) values ('01/01/2020','12/31/2020',3,10,1);
insert into core.tb_promocao (inicio_vigencia, fim_vigencia, quantidade,valor,id_produto) values ('01/01/2020','12/31/2020',3,10,2);
insert into core.tb_promocao (inicio_vigencia, fim_vigencia, quantidade,valor,id_produto) values ('01/01/2020','12/31/2020',3,10,3);

insert into core.tb_endpoint (url,descricao,left_menu) values ('/membros','Manutenção de membros do MC',true);
insert into core.tb_endpoint (url,descricao,left_menu) values ('/lancamento-novo','Manutenção de lançamentos de débito/crédito',true);

insert into core.tb_regra_acesso (rules_acesso) values (0);
insert into core.tb_regra_acesso (rules_acesso) values (1);
insert into core.tb_regra_acesso (rules_acesso) values (2);
insert into core.tb_regra_acesso (rules_acesso) values (3);
insert into core.tb_regra_acesso (rules_acesso) values (4);
insert into core.tb_regra_acesso (rules_acesso) values (5);

select * from core.tb_endpoint_regra_acesso

--perfil de desenvolvedor
insert into core.tb_endpoint_regra_acesso (id_endpoint, id_regra_acesso) values (1,1);
insert into core.tb_endpoint_regra_acesso (id_endpoint, id_regra_acesso) values (2,1);

--perfil de tesoureiro
insert into core.tb_endpoint_regra_acesso (id_endpoint, id_regra_acesso) values (2,2);

--perfil de secretario
insert into core.tb_endpoint_regra_acesso (id_endpoint, id_regra_acesso) values (1,3);

--inserir perfil membro
--insert into core.tb_membro_regra_acesso (id_membro,id_regra_acesso) values(5,2);



