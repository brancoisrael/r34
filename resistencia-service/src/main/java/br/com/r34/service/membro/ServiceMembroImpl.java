package br.com.r34.service.membro;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.r34.persistencia.dto.membro.MembroDTO;
import br.com.r34.persistencia.enums.SituacaoMembro;
import br.com.r34.persistencia.repository.membro.MembroDAO;
import br.com.r34.persistencia.repository.membro.SaldoMembroDAO;
import br.com.r34.persistencia.vo.membro.Membro;
import br.com.r34.persistencia.vo.membro.SaldoMembro;
import br.com.r34.service.util.CipherUtil;

@Service
public class ServiceMembroImpl {

	@Autowired
	private MembroDAO membroDAO;

	private MembroDTO membroDTO;
	
	@Autowired
	private SaldoMembroDAO saldoMembroDAO;

	@Transactional(transactionManager="resistenciaTransactionManager")	
	public MembroDTO inserir(Membro membro) {
		membroDTO = new MembroDTO();
		membroDTO.setMessage("Erro ao inserir membro");

		if(membro.getSituacaoMembro()==SituacaoMembro.DESLIGADO && membro.getDataSaida()==null) {
			membroDTO.setMessage("Informe a data de saída do infeliz.");
			return membroDTO;
		}
		
		if(membro.getCargo()!=null &&
			membroDAO.findByCargo(membro.getCargo(),membro.getId())>0) {
			membroDTO.setMessage("Outro infeliz já possui este cargo, mude esta porra e tente novamente.");
			return membroDTO;
		}
		
		try {
			membro.setSenha(CipherUtil.cipher(membro.getSenha()));
			Membro m = membroDAO.save(membro);
			if (m != null) {
				membroDTO.setSucesso(true);
				membroDTO.setMessage("Membro inserido com sucesso");
			}
		} catch (DataIntegrityViolationException e) {
			membroDTO.setSucesso(false);
			membroDTO.setMessage("Membro já existente, revise os dados e tente novamente.");
			Logger.getLogger(e.getMessage());
		} catch (ConstraintViolationException e) {
			membroDTO.setSucesso(false);
			membroDTO.setMessage(e.getConstraintViolations().stream().findAny().get().getMessage());
			Logger.getLogger(e.getMessage());
		} catch (Exception e) {
			membroDTO.setSucesso(false);
			membroDTO.setMessage("Erro ao tentar inserir membro. ");
			Logger.getLogger(e.getMessage());
		}
		return membroDTO;
	}

	@Transactional(transactionManager="resistenciaTransactionManager")
	
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

	@Transactional(transactionManager="resistenciaTransactionManager")
	
	public MembroDTO atualizar(Membro membro) {
		membroDTO = new MembroDTO();
		membroDTO.setMessage("Erro ao atualizar membro");
		
		if(membro.getSituacaoMembro()==SituacaoMembro.DESLIGADO && membro.getDataSaida()==null) {
			membroDTO.setMessage("Informe a data de saída do infeliz.");
			return membroDTO;
		}
		
		if(membro.getCargo()!=null &&
				membroDAO.findByCargo(membro.getCargo(),membro.getId())>0) {
			membroDTO.setMessage("Outro infeliz já possui este cargo, mude esta porra e tente novamente.");
			return membroDTO;
		}
		
		try {
			Membro m = membroDAO.save(membro);
			if (m != null) {
				membroDTO.setSucesso(true);
				membroDTO.setMessage("Membro atualizado com sucesso");
			}
		} catch (DataIntegrityViolationException e) {
			membroDTO.setSucesso(false);
			membroDTO.setMessage("Membro já existente, revise os dados e tente novamente.");
			Logger.getLogger(e.getMessage());
		} catch (ConstraintViolationException e) {
			membroDTO.setSucesso(false);
			membroDTO.setMessage(e.getConstraintViolations().stream().findAny().get().getMessage());
			Logger.getLogger(e.getMessage());
		} catch (Exception e) {
			membroDTO.setSucesso(false);
			membroDTO.setMessage("Erro ao tentar inserir membro. ");
			Logger.getLogger(e.getMessage());
		}

		return membroDTO;
	}

	
	public MembroDTO login(MembroDTO membroDTO) {
		membroDTO = new MembroDTO();

		return membroDTO;
	}

	@Transactional(transactionManager="resistenciaTransactionManager")
	
	public Iterable<Membro> selectAll() {
		return membroDAO.selectAll();
	}

	@Transactional(transactionManager="resistenciaTransactionManager")
	
	public Membro buscarId(long id) {
		return membroDAO.findById(id);
	}
	
	@Transactional(transactionManager="resistenciaTransactionManager")
	
	public List<Membro> selectByStatus(boolean status){
		return membroDAO.selectByStatus(status);
	}
	
	@Transactional(transactionManager="resistenciaTransactionManager")
	
	public SaldoMembro selectSaldoByMembro(long idMembro) {
		return saldoMembroDAO.selectByMembro(idMembro);
	}

	public void setMembroDAO(MembroDAO membroDAO) {
		this.membroDAO = membroDAO;
	}

}
