alter TABLE core.tb_promocao add constraint uk_inicio_vigencia unique (inicio_vigencia,id_produto);
alter TABLE core.tb_promocao add constraint uk_fim_vigencia unique (fim_vigencia,id_produto);
alter TABLE core.tb_saldo_membro drop column saldo;
alter TABLE core.tb_saldo_membro add saldo double precision NOT NULL default 0;
alter table core.tb_saldo_membro ADD  FOREIGN KEY (id_membro) REFERENCES core.tb_membro (id) ON DELETE CASCADE;