alter TABLE tb_promocao add constraint uk_inicio_vigencia unique (inicio_vigencia,id_produto);
alter TABLE tb_promocao add constraint uk_fim_vigencia unique (fim_vigencia,id_produto);
alter TABLE public.tb_saldo_membro drop column saldo;
alter TABLE public.tb_saldo_membro add saldo double precision NOT NULL default 0;