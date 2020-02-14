package br.com.r34.persistencia.repository.membro;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.r34.persistencia.vo.membro.SaldoMembro;

public interface SaldoMembroDAO extends CrudRepository<SaldoMembro, Long> {

	@Query("select s from SaldoMembro s "
			+ "inner join s.membro m "
			+ "inner join m.lancamentos l "
			+ "where l.id=:idLancamento")
	SaldoMembro selectByLancamento(@Param("idLancamento")long idLancamento);
	
	@Query("select s from SaldoMembro s "
			+ "inner join s.membro m "
			+ "where m.id=:idMembro")
	SaldoMembro selectByMembro(@Param("idMembro")long idMembro);
			
	@Modifying
	@Query(nativeQuery=true,value="update tb_saldo_membro set saldo =( " + 
			"(select sum(valor) from " + 
			"(select case when (select sum(valor_lancamento) from tb_lancamento where tipo_lancamento=1 and id_membro=:idMembro) is null then 0 " + 
			"else (select sum(valor_lancamento) from tb_lancamento where tipo_lancamento=1 and id_membro=:idMembro) end as valor from tb_lancamento " + 
			") as valor) - " + 
			"(select case when (select sum(valor_lancamento) from tb_lancamento where tipo_lancamento=0 and id_membro=:idMembro) is null then 0 " + 
			"else (select sum(valor_lancamento) from tb_lancamento where tipo_lancamento=0 and id_membro=:idMembro) end as valor2 from tb_lancamento)) " + 
			"where id_membro=:idMembro")
	int updateSaldoMembro(@Param("idMembro") long idMembro);
	
}
