package br.com.r34.persistencia.repository.lancamento;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.r34.persistencia.vo.lancamento.Promocao;

public interface PromocaoDAO extends CrudRepository<Promocao, Long>{

	@Query("select promocao from Promocao promocao "
				+ "inner join promocao.produto produto "
				+ "where produto.id=:idProduto and "
				+ "((:dataLancamento between promocao.inicioVigencia and promocao.fimVigencia) "
				+ "or (promocao.inicioVigencia <=:dataLancamento and promocao.fimVigencia is null))")
		Promocao perquisarPorProduto(@Param("idProduto") long idProduto, @Param("dataLancamento") Date dataLancamento);
}
