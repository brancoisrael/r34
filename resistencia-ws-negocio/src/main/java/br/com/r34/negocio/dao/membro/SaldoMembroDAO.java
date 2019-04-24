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
		
}
