package br.com.r34.service.lancamento;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.r34.persistencia.repository.lancamento.TemplateLancamentoDAO;
import br.com.r34.persistencia.vo.lancamento.TemplateLancamento;
import br.com.r34.persistencia.dto.lancamento.TemplateLancamentoDTO;

@Service
public class TemplateLancamentoImpl {

	@Autowired
	private TemplateLancamentoDAO templateLancamentoDAO;
	
	
	public TemplateLancamentoDTO inserir(TemplateLancamento templateLancamento) {
		TemplateLancamentoDTO templateLancamentoDTO = new TemplateLancamentoDTO();
		
		try {
			templateLancamento = templateLancamentoDAO.save(templateLancamento);
			templateLancamentoDTO.setMessage("Lançamento inserido com sucesso.");
			templateLancamentoDTO.setSucesso(true);			
		} 
		catch (ConstraintViolationException e) {
			templateLancamentoDTO.setMessage(e.getConstraintViolations().iterator().next().getMessageTemplate());
		}
		catch(DataIntegrityViolationException e) {
			templateLancamentoDTO.setMessage("Campo único no banco de dados: "+ e.getMostSpecificCause());
		}
		
		return templateLancamentoDTO;
	}

	
	public TemplateLancamentoDTO deletar(TemplateLancamento templateLancamento) {
		TemplateLancamentoDTO templateLancamentoDTO = new TemplateLancamentoDTO();
		
		try {
			templateLancamentoDAO.delete(templateLancamento);
			templateLancamentoDTO.setSucesso(true);
			templateLancamentoDTO.setMessage("Lançamento excluído com sucesso");
		} 
		catch (IllegalArgumentException e) {
			templateLancamentoDTO.setMessage("Lançamento não pode ser excluído");
		}
		catch (ConstraintViolationException e) {
			templateLancamentoDTO.setMessage(e.getConstraintViolations().iterator().next().getMessageTemplate());
		}

		return templateLancamentoDTO;
	}

	
	public TemplateLancamentoDTO atualizar(TemplateLancamento templateLancamento) {
		TemplateLancamentoDTO templateLancamentoDTO = new TemplateLancamentoDTO();
		
		try {
			templateLancamento = templateLancamentoDAO.save(templateLancamento);
			templateLancamentoDTO.setMessage("Lançamento atualizado com sucesso.");
			templateLancamentoDTO.setSucesso(true);			
		} 
		catch (ConstraintViolationException e) {
			templateLancamentoDTO.setMessage(e.getConstraintViolations().iterator().next().getMessageTemplate());
		}
		catch(DataIntegrityViolationException e) {
			templateLancamentoDTO.setMessage("Campo único no banco de dados: "+ e.getMostSpecificCause());
		}
		
		return templateLancamentoDTO;
	}

	
	public List<TemplateLancamento> pesquisarPorMembro(long idMembro) {
		return templateLancamentoDAO.perquisarPorMembro(idMembro);
	}

}
