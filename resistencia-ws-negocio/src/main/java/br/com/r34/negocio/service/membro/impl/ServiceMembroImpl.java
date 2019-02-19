package br.com.r34.negocio.service.membro.impl;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.r34.negocio.dao.membro.MembroDAO;
import br.com.r34.negocio.domain.dto.membro.MembroDTO;
import br.com.r34.negocio.domain.vo.membro.Membro;
import br.com.r34.negocio.service.membro.ServiceMembro;

@Service

public class ServiceMembroImpl implements ServiceMembro {

	@Autowired
	private MembroDAO membroDAO;

	private MembroDTO membroDTO;

	@Override
	public MembroDTO inserir(Membro membro) {
		membroDTO = new MembroDTO();
		membroDTO.setMessage("Erro ao inserir membro");

		try {
			Membro m = membroDAO.save(membro);
			if (m != null) {
				membroDTO.setSucesso(true);
				membroDTO.setMessage("Membro inserido com sucesso");
			}
		}
		catch(DataIntegrityViolationException e) {
			membroDTO.setSucesso(false);
			membroDTO.setMessage("Membro já existente, revise os dados e tente novamente.");
			Logger.getLogger(e.getMessage());
		}
		catch(ConstraintViolationException e) {
			membroDTO.setSucesso(false);
			membroDTO.setMessage(e.getConstraintViolations().stream().findAny().get().getMessage());
			Logger.getLogger(e.getMessage());
		}
		catch (Exception e) {
			membroDTO.setSucesso(false);
			membroDTO.setMessage("Erro ao tentar inserir membro. ");
			Logger.getLogger(e.getMessage());
		}
		return membroDTO;
	}

	@Override
	public MembroDTO deletar(long id) {
		membroDTO = new MembroDTO();

		Membro membro = new Membro();
		membro.setId(id);
		try {
			membroDAO.delete(membro);
			membroDTO.setSucesso(true);
			membroDTO.setMessage("Excluído com sucesso");
		} catch (IllegalArgumentException e) {
			membroDTO.setMessage("Membro não pode ser excluído");
		}

		return membroDTO;
	}

	@Override
	public MembroDTO atualizar(Membro membro) {
		membroDTO = new MembroDTO();
		membroDTO.setMessage("Erro ao inserir membro");

		Membro m = membroDAO.save(membro);
		if (m != null) {
			membroDTO.setSucesso(true);
			membroDTO.setMessage("Memro atualizado com sucesso");
		}

		return membroDTO;
	}

	@Override
	public MembroDTO login(MembroDTO membroDTO) {
		membroDTO = new MembroDTO();

		return membroDTO;
	}

	@Override
	public Iterable<Membro> selectAll(){
		return membroDAO.selectAll();
	}
	
	public void setMembroDAO(MembroDAO membroDAO) {
		this.membroDAO = membroDAO;
	}

}
