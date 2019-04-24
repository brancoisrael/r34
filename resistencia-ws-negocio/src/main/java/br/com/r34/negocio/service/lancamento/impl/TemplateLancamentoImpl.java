package br.com.r34.negocio.service.lancamento.impl;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.r34.negocio.dao.lancamento.TemplateLancamentoDAO;
import br.com.r34.negocio.domain.dto.lancamento.TemplateLancamentoDTO;
import br.com.r34.negocio.domain.vo.lancamento.TemplateLancamento;
import br.com.r34.negocio.service.lancamento.ServiceLancamento;

@Service
public class TemplateLancamentoImpl implements ServiceLancamento<TemplateLancamento,TemplateLancamentoDTO> {

	@Autowired
	private TemplateLancamentoDAO templateLancamentoDAO;
	
	@Override
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

	@Override
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

	@Override
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

	@Override
	public List<TemplateLancamento> pesquisarPorMembro(long idMembro) {
		return templateLancamentoDAO.perquisarPorMembro(idMembro);
	}

}
