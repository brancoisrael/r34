package br.com.r34.negocio.service.acesso.impl;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.r34.negocio.dao.acesso.RegraAcessoDAO;
import br.com.r34.negocio.domain.dto.acesso.RegraAcessoDTO;
import br.com.r34.negocio.domain.vo.acesso.RegraAcesso;
import br.com.r34.negocio.service.acesso.ServiceRegraAcesso;

@Service
public class ServiceRegraAcessoImpl implements ServiceRegraAcesso {

	@Autowired
	private RegraAcessoDAO regraAcessoDAO;
	
	@Override
	public RegraAcessoDTO inserir(RegraAcesso regraAcesso) {
		RegraAcessoDTO regraAcessoDTO = new RegraAcessoDTO();
		regraAcessoDTO.setMessage("Erro ao inserir regraAcesso");
		
		try {	
			regraAcesso= regraAcessoDAO.save(regraAcesso);
			regraAcessoDTO.setMessage("RegraAcesso inserido com sucesso.");
			regraAcessoDTO.setSucesso(true);
		} 
		catch (ConstraintViolationException e) {
			regraAcessoDTO.setMessage(e.getConstraintViolations().iterator().next().getMessageTemplate());
		}
		catch(DataIntegrityViolationException e) {
			regraAcessoDTO.setMessage("Campo único no banco de dados: "+ e.getMostSpecificCause());
		}
		catch(Exception e) {
			regraAcessoDTO.setMessage("Campo único no banco de dados: "+ e.getMessage());
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
			regraAcessoDTO.setMessage("Regra de acesso excluída com sucesso");
		}
		catch (IllegalArgumentException e) {
			regraAcessoDTO.setMessage("Regras de acesso não pode ser excluída");
		}
		catch (ConstraintViolationException e) {
			regraAcessoDTO.setMessage(e.getConstraintViolations().iterator().next().getMessageTemplate());
		}		
		
		return regraAcessoDTO;
	}

	@Override
	public RegraAcessoDTO atualizar(RegraAcesso regraAcesso) {
		RegraAcessoDTO regraAcessoDTO = new RegraAcessoDTO();
		regraAcessoDTO.setMessage("Erro ao atualizar regra de acesso");
		
		try {	
			regraAcesso= regraAcessoDAO.save(regraAcesso);
			regraAcessoDTO.setMessage("RegraAcesso atualizado com sucesso.");
			regraAcessoDTO.setSucesso(true);
		} 
		catch (ConstraintViolationException e) {
			regraAcessoDTO.setMessage(e.getConstraintViolations().iterator().next().getMessageTemplate());
		}
		catch(DataIntegrityViolationException e) {
			regraAcessoDTO.setMessage("Campo único no banco de dados: "+ e.getMostSpecificCause());
		}
		
		return regraAcessoDTO;
	}

	@Override
	public List<RegraAcesso> pesquisarTodos() {
		return regraAcessoDAO.findAll();
	}

}
