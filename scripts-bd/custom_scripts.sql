alter TABLE tb_promocao add constraint uk_inicio_vigencia unique (inicio_vigencia,id_produto);
alter TABLE tb_promocao add constraint uk_fim_vigencia unique (fim_vigencia,id_produto);
