package br.com.r34.negocio.service.lancamento;

import java.util.List;

public interface ServiceLancamento<T,Z> {

	Z inserir(T t);	
	
	Z deletar(long id);
	
	Z atualizar(T t);
	
	List<T> pesquisarPorMembro(long idMembro);
}
