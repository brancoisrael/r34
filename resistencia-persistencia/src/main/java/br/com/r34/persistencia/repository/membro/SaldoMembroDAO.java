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
	@Query(nativeQuery=true,value="update core.tb_saldo_membro set saldo =:saldo where id_membro=:idMembro")
	void updateSaldoMembro(@Param("saldo") double saldo, @Param("idMembro") long idMembro);
	
}
