package br.com.r34.negocio.service.lancamento.impl;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.r34.negocio.dao.lancamento.DescricaoLancamentoDAO;
import br.com.r34.negocio.domain.dto.lancamento.DescricaoLancamentoDTO;
import br.com.r34.negocio.domain.vo.lancamento.DescricaoLancamento;
import br.com.r34.negocio.service.lancamento.ServiceDescricaoLancamento;

@Service
public class ServiceDescricaoLancamentoImpl implements ServiceDescricaoLancamento<DescricaoLancamento, DescricaoLancamentoDTO> {

	@Autowired
	private DescricaoLancamentoDAO descricaoLancamentoDAO;
	
	@Override
	public DescricaoLancamentoDTO inserir(DescricaoLancamento descricaoLancamento) {
		DescricaoLancamentoDTO descricaoLancamentoDTO = new DescricaoLancamentoDTO();
		
		try {
			descricaoLancamento = descricaoLancamentoDAO.save(descricaoLancamento);
			descricaoLancamentoDTO.setMessage("Descricao do lançamento inserido com sucesso.");
			descricaoLancamentoDTO.setSucesso(true);			
		} 
		catch (ConstraintViolationException e) {
			descricaoLancamentoDTO.setMessage(e.getConstraintViolations().iterator().next().getMessageTemplate());
		}
		catch(DataIntegrityViolationException e) {
			descricaoLancamentoDTO.setMessage("Campo único no banco de dados: "+ e.getMostSpecificCause());
		}
		
		return descricaoLancamentoDTO;
	}

	@Override
	public DescricaoLancamentoDTO deletar(long id) {
		DescricaoLancamentoDTO descricaoLancamentoDTO = new DescricaoLancamentoDTO();

		DescricaoLancamento descricaoLancamento = new DescricaoLancamento();
		descricaoLancamento.setId(id);
		try {
			descricaoLancamentoDAO.delete(descricaoLancamento);
			descricaoLancamentoDTO.setSucesso(true);
			descricaoLancamentoDTO.setMessage("Descricao do lançamento excluído com sucesso");
		} 
		catch (IllegalArgumentException e) {
			descricaoLancamentoDTO.setMessage("Descricao do lançamento não pode ser excluído");
		}
		catch (ConstraintViolationException e) {
			descricaoLancamentoDTO.setMessage(e.getConstraintViolations().iterator().next().getMessageTemplate());
		}

		return descricaoLancamentoDTO;
	}

	@Override
	public DescricaoLancamentoDTO atualizar(DescricaoLancamento descricaoLancamento) {
		DescricaoLancamentoDTO descricaoLancamentoDTO = new DescricaoLancamentoDTO();
		
		try {
			descricaoLancamento = descricaoLancamentoDAO.save(descricaoLancamento);
			descricaoLancamentoDTO.setMessage("Descricao do lançamento atualizado com sucesso.");
			descricaoLancamentoDTO.setSucesso(true);			
		} 
		catch (ConstraintViolationException e) {
			descricaoLancamentoDTO.setMessage(e.getConstraintViolations().iterator().next().getMessageTemplate());
		}
		catch(DataIntegrityViolationException e) {
			descricaoLancamentoDTO.setMessage("Campo único no banco de dados: "+ e.getMostSpecificCause());
		}
		
		return descricaoLancamentoDTO;
	}

	@Override
	public List<DescricaoLancamento> findAllByOrderByDescricao() {
		return descricaoLancamentoDAO.findAllByOrderByDescricao();
	}

	@Override
	public List<DescricaoLancamento> findByDescricao(String descricao) {
		return descricaoLancamentoDAO.findByDescricao(descricao);
	}
	
	@Override
	public List<DescricaoLancamento> buscarPorDescricao(String descricao) {
		return descricaoLancamentoDAO.buscarPorDescricao(descricao);
	}
}
