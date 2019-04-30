package br.com.r34.negocio.dao.membro;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.r34.negocio.domain.vo.membro.SaldoMembro;

public interface SaldoMembroDAO extends CrudRepository<SaldoMembro, Long> {

	@Query("select s from SaldoMembro s "
			+ "inner join s.membro m "
			+ "inner join m.lancamentos l "
			+ "where l.id=:idLancamento")
	SaldoMembro selectBylancamento(@Param("idLancamento")long idLancamento);
		
	
	/* atualizar saldo
	 
	 update tb_saldo_membro set saldo =(
	(select sum(valor) from
(select case when (select sum(valor_lancamento) from tb_lancamento where tipo_lancamento=1 and id_membro=66) is null then 0 
else (select sum(valor_lancamento) from tb_lancamento where tipo_lancamento=1 and id_membro=66) end as valor from tb_lancamento
) as valor) -
	(select sum(valor_lancamento) from tb_lancamento where tipo_lancamento=0 and id_membro=66))
where id_membro=66;
*/
}
