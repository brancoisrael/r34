package br.com.r34.service.lancamento;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.r34.persistencia.dto.lancamento.LancamentoDTO;
import br.com.r34.persistencia.enums.OrigemLancamento;
import br.com.r34.persistencia.enums.StatusLancamento;
import br.com.r34.persistencia.repository.lancamento.LancamentoDAO;
import br.com.r34.persistencia.repository.membro.SaldoMembroDAO;
import br.com.r34.persistencia.vo.lancamento.Lancamento;

@Service
public class ServiceLancamentoImpl{

	@Autowired
	private LancamentoDAO lancamentoDAO;
	
	@Autowired
	private SaldoMembroDAO saldoMembroDAO;
		
	@Autowired
	private ServicePromocaoImpl servicePromocao;
		
	@Transactional(transactionManager="resistenciaTransactionManager")	
	public LancamentoDTO inserir(Lancamento lanc) {
		LancamentoDTO lancamentoDTO = new LancamentoDTO();
		
		for(int i=0; i<lanc.getQuantidade();i++) {
			
			try {					
				Lancamento lancamento = lanc.clone();
				lancamento.setQuantidade(1);
				
				if(lancamento.getOrigemLancamento()==OrigemLancamento.BAR &&
						(lancamento.getProdutoVenda()==null || lancamento.getTipoLancamento()==null)) {
					lancamentoDTO.setSucesso(false);
					lancamentoDTO.setMessage("Quando o lançamento for do tipo BAR, é obrigatório informar o produto.");
					return lancamentoDTO;
				}
				
				if(lancamento.getValorLancamento()<1) {
					lancamentoDTO.setSucesso(false);
					lancamentoDTO.setMessage("Valor do lançamento tem que ser maior que zero.");
					return lancamentoDTO;
				}
				
				lancamento.setResposavelLancamento(lancamento.getMembro());
				lancamento.setCriadoEm(new Date());
				lancamento.setStatusLancamento(StatusLancamento.AGUARDANDO_QUITACAO);			
			
				lancamento = lancamentoDAO.save(lancamento);						
								
				servicePromocao.acrescentarPromocao(lancamento);
				lancamentoDTO.getSaldoMembro().setSaldo(atualizarSaldoMembro(lanc.getMembro().getId()));
				
				lancamentoDTO.addLancamento(lancamento);		
				lancamentoDTO.setMessage(lanc.getQuantidade() +" lançamento(s) inserido(s) com sucesso.");
				lancamentoDTO.setSucesso(true);					
			} 
			
			catch (ConstraintViolationException e) {
				lancamentoDTO.setSucesso(false);
				lancamentoDTO.setMessage(e.getConstraintViolations().iterator().next().getMessageTemplate());
				break;
			}
			catch(DataIntegrityViolationException e) {
				lancamentoDTO.setSucesso(false);
				lancamentoDTO.setMessage("Campo único no banco de dados: "+ e.getMostSpecificCause());
				break;
			}
			catch (Exception  e) {
				lancamentoDTO.setSucesso(false);
				lancamentoDTO.setMessage("Erro ao tentar inserir lançamento. ");
				Logger.getLogger(e.getMessage());
				break;
			}
		}
		
		return lancamentoDTO;
	}
	
	@Transactional(transactionManager="resistenciaTransactionManager")	
	public LancamentoDTO deletar(Lancamento lanc) {
		LancamentoDTO lancamentoDTO = new LancamentoDTO();
		try {
			Lancamento lancamento = lanc.clone();
			servicePromocao.removerPromocao(lancamento);
			
			lancamentoDAO.delete(lancamento);
						
			lancamentoDTO.getSaldoMembro().setSaldo(atualizarSaldoMembro(lanc.getMembro().getId()));	
			lancamentoDTO.setSucesso(true);
			lancamentoDTO.setMessage("Lançamento excluído com sucesso");
		} 
		catch (IllegalArgumentException e) {
			lancamentoDTO.setMessage("Lançamento não pode ser excluído");
		}
		catch (ConstraintViolationException e) {
			lancamentoDTO.setMessage(e.getConstraintViolations().iterator().next().getMessageTemplate());
		}
		catch (Exception  e) {
			lancamentoDTO.setSucesso(false);
			lancamentoDTO.setMessage("Erro ao tentar excluir lançamento. ");
			Logger.getLogger(e.getMessage());			
		}

		return lancamentoDTO;
	}

	@Transactional(transactionManager="resistenciaTransactionManager")	
	public LancamentoDTO atualizar(Lancamento lancamento) {
		LancamentoDTO lancamentoDTO = new LancamentoDTO();
		
		try {
			lancamento = lancamentoDAO.save(lancamento);
			lancamentoDTO.setMessage("Lançamento atualizado com sucesso.");
			lancamentoDTO.setSucesso(true);			
		} 
		catch (ConstraintViolationException e) {
			lancamentoDTO.setSucesso(false);
			lancamentoDTO.setMessage(e.getConstraintViolations().iterator().next().getMessageTemplate());
		}
		catch(DataIntegrityViolationException e) {
			lancamentoDTO.setSucesso(false);
			lancamentoDTO.setMessage("Campo único no banco de dados: "+ e.getMostSpecificCause());
		}
		catch (Exception e) {
			lancamentoDTO.setSucesso(false);
			lancamentoDTO.setMessage("Erro ao tentar inserir lançameto. ");
			Logger.getLogger(e.getMessage());
		}
		
		return lancamentoDTO;
	}

	public List<Lancamento> pesquisarPorMembro(long idMembro) {
		return lancamentoDAO.perquisarPorMembro(idMembro);
	}
	
	public List<Lancamento> pesquisarPorMembro(String email) {
		return lancamentoDAO.perquisarPorMembro(email);
	}
	
	private double atualizarSaldoMembro(long idMembro) {
		Double[] credDeb = lancamentoDAO.somarLancamento(idMembro);
		if(credDeb[0]==null)
			credDeb[0]=0D;
		
		if(credDeb[1]==null)
			credDeb[1]=0D;
		
		double total = credDeb[1] - credDeb[0];
		DecimalFormat df = new DecimalFormat("#.00");
		total = Double.parseDouble(df.format(total).replace(",","."));
		saldoMembroDAO.updateSaldoMembro(total,idMembro);
		
		return total;
	}

}
