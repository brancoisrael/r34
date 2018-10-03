package br.com.r34.negocio.service.acesso;

import java.util.List;

import br.com.r34.negocio.domain.dto.acesso.RegraAcessoDTO;
import br.com.r34.negocio.domain.vo.acesso.RegraAcesso;

public interface ServiceRegraAcesso {

	RegraAcessoDTO inserir(RegraAcesso regraAcesso);	
	
	RegraAcessoDTO deletar(long id);
	
	RegraAcessoDTO atualizar(RegraAcesso regraAcesso);
	
	List<RegraAcesso> pesquisarTodos();
}
