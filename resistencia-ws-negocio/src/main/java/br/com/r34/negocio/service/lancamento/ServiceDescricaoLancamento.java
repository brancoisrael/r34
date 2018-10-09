package br.com.r34.negocio.service.lancamento;

import java.util.List;

public interface ServiceDescricaoLancamento<T,Z> {

	Z inserir(T t);	
	
	Z deletar(long id);
	
	Z atualizar(T t);
	
	List<T> findAllByOrderByDescricao();
	
	List<T> findByDescricao(String descricao);
	
	List<T>  buscarPorDescricao(String descricao);
}
