package br.com.r34.service.acesso;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.r34.persistencia.dto.acesso.RegraAcessoDTO;
import br.com.r34.persistencia.repository.acesso.RegraAcessoDAO;
import br.com.r34.persistencia.vo.acesso.RegraAcesso;

@Service
public class ServiceRegraAcessoImpl {

	@Autowired
	private RegraAcessoDAO regraAcessoDAO;
	
	
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

	
	public List<RegraAcesso> pesquisarTodos() {
		return regraAcessoDAO.findAll();
	}

}
