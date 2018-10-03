package br.com.r34.negocio.service.acesso.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.r34.negocio.dao.acesso.RegraAcessoDAO;
import br.com.r34.negocio.domain.dto.acesso.RegraAcessoDTO;
import br.com.r34.negocio.domain.vo.acesso.RegraAcesso;
import br.com.r34.negocio.service.acesso.ServiceRegraAcesso;

public class ServiceRegraAcessoImpl implements ServiceRegraAcesso {

	@Autowired
	private RegraAcessoDAO regraAcessoDAO;
	
	@Override
	public RegraAcessoDTO inserir(RegraAcesso regraAcesso) {
		RegraAcessoDTO regraAcessoDTO = new RegraAcessoDTO();
		regraAcessoDTO.setMessage("Erro ao inserir regraAcesso");
		
		RegraAcesso e = regraAcessoDAO.save(regraAcesso);
		if(e!=null) {
			regraAcessoDTO.setMessage("RegraAcesso inserido com sucesso.");
			regraAcessoDTO.setSucesso(true);
		}
		
		return regraAcessoDTO;
	}

	@Override
	public RegraAcessoDTO deletar(long id) {
		RegraAcessoDTO regraAcessoDTO  = new RegraAcessoDTO();
		
		RegraAcesso regraAcesso= new RegraAcesso();
		regraAcesso.setId(id);
		try {
			regraAcessoDAO.delete(regraAcesso);
			regraAcessoDTO.setSucesso(true);
			regraAcessoDTO.setMessage("EndPoint excluído com sucesso");
		}
		catch(IllegalArgumentException e) {
			regraAcessoDTO.setMessage("EndPoint não pode ser excluído");
		}		
		
		return regraAcessoDTO;
	}

	@Override
	public RegraAcessoDTO atualizar(RegraAcesso regraAcesso) {
		RegraAcessoDTO regraAcessoDTO = new RegraAcessoDTO();
		regraAcessoDTO.setMessage("Erro ao inserir regraAcesso");
		
		RegraAcesso e = regraAcessoDAO.save(regraAcesso);
		if(e!=null) {
			regraAcessoDTO.setMessage("RegraAcesso inserido com sucesso.");
			regraAcessoDTO.setSucesso(true);
		}
		
		return regraAcessoDTO;
	}

	@Override
	public List<RegraAcesso> pesquisarTodos() {
		return regraAcessoDAO.findAll();
	}

}
