package br.com.r34.negocio.service.lancamento.impl;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.r34.negocio.dao.lancamento.LancamentoDAO;
import br.com.r34.negocio.domain.dto.lancamento.LancamentoDTO;
import br.com.r34.negocio.domain.vo.lancamento.Lancamento;
import br.com.r34.negocio.service.lancamento.ServiceLancamento;

@Service
public class ServiceLancamentoImpl implements ServiceLancamento<Lancamento,LancamentoDTO> {

	@Autowired
	private LancamentoDAO lancamentoDAO;
	
	@Override
	public LancamentoDTO inserir(Lancamento lancamento) {
		LancamentoDTO lancamentoDTO = new LancamentoDTO();
		
		try {
			lancamento = lancamentoDAO.save(lancamento);
			lancamentoDTO.setMessage("Lançamento inserido com sucesso.");
			lancamentoDTO.setSucesso(true);			
		} 
		catch (ConstraintViolationException e) {
			lancamentoDTO.setMessage(e.getConstraintViolations().iterator().next().getMessageTemplate());
		}
		catch(DataIntegrityViolationException e) {
			lancamentoDTO.setMessage("Campo único no banco de dados: "+ e.getMostSpecificCause());
		}
		
		return lancamentoDTO;
	}

	@Override
	public LancamentoDTO deletar(long id) {
		LancamentoDTO lancamentoDTO = new LancamentoDTO();

		Lancamento lancamento = new Lancamento();
		lancamento.setId(id);
		try {
			lancamentoDAO.delete(lancamento);
			lancamentoDTO.setSucesso(true);
			lancamentoDTO.setMessage("Lançamento excluído com sucesso");
		} 
		catch (IllegalArgumentException e) {
			lancamentoDTO.setMessage("Lançamento não pode ser excluído");
		}
		catch (ConstraintViolationException e) {
			lancamentoDTO.setMessage(e.getConstraintViolations().iterator().next().getMessageTemplate());
		}

		return lancamentoDTO;
	}

	@Override
	public LancamentoDTO atualizar(Lancamento lancamento) {
		LancamentoDTO lancamentoDTO = new LancamentoDTO();
		
		try {
			lancamento = lancamentoDAO.save(lancamento);
			lancamentoDTO.setMessage("Lançamento atualizado com sucesso.");
			lancamentoDTO.setSucesso(true);			
		} 
		catch (ConstraintViolationException e) {
			lancamentoDTO.setMessage(e.getConstraintViolations().iterator().next().getMessageTemplate());
		}
		catch(DataIntegrityViolationException e) {
			lancamentoDTO.setMessage("Campo único no banco de dados: "+ e.getMostSpecificCause());
		}
		
		return lancamentoDTO;
	}

	@Override
	public List<Lancamento> pesquisarPorMembro(long idMembro) {
		return lancamentoDAO.perquisarPorMembro(idMembro);
	}

}
