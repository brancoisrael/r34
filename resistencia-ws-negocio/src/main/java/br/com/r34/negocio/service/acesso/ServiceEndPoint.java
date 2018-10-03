package br.com.r34.negocio.service.acesso;

import java.util.List;

import br.com.r34.negocio.domain.dto.acesso.EndPointDTO;
import br.com.r34.negocio.domain.vo.acesso.Endpoint;

public interface ServiceEndPoint {

	EndPointDTO inserir(Endpoint endpoint);	
	
	EndPointDTO deletar(long id);
	
	EndPointDTO atualizar(Endpoint endpoint);
	
	List<Endpoint> pesquisarTodos();
	
	List<Endpoint> pesquisarDescricao(String descricao);
	
}
