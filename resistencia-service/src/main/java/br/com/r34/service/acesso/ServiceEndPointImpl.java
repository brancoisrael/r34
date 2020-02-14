package br.com.r34.service.acesso;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.r34.persistencia.dto.acesso.EndPointDTO;
import br.com.r34.persistencia.repository.acesso.EndPointDAO;
import br.com.r34.persistencia.vo.acesso.Endpoint;

@Service
public class ServiceEndPointImpl {

	@Autowired
	private EndPointDAO endPointDAO;

	
	public EndPointDTO inserir(Endpoint endpoint) {
		EndPointDTO endPointDTO = new EndPointDTO();

		try {
			endpoint = endPointDAO.save(endpoint);
			endPointDTO.setMessage("Endpoint inserido com sucesso.");
			endPointDTO.setSucesso(true);			
		} 
		catch (ConstraintViolationException e) {
			endPointDTO.setMessage(e.getConstraintViolations().iterator().next().getMessageTemplate());
		}
		catch(DataIntegrityViolationException e) {
			endPointDTO.setMessage("Campo único no banco de dados: "+ e.getMostSpecificCause());
		}
		
		return endPointDTO;
	}

	
	public EndPointDTO deletar(long id) {
		EndPointDTO endPointDTO = new EndPointDTO();

		Endpoint endpoint = new Endpoint();
		endpoint.setId(id);
		try {
			endPointDAO.delete(endpoint);
			endPointDTO.setSucesso(true);
			endPointDTO.setMessage("EndPoint excluído com sucesso");
		} 
		catch (IllegalArgumentException e) {
			endPointDTO.setMessage("EndPoint não pode ser excluído");
		}
		catch (ConstraintViolationException e) {
			endPointDTO.setMessage(e.getConstraintViolations().iterator().next().getMessageTemplate());
		}

		return endPointDTO;
	}

	
	public EndPointDTO atualizar(Endpoint endpoint) {
		EndPointDTO endPointDTO = new EndPointDTO();
		endPointDTO.setMessage("Erro ao inserir endpoint");

		try {
			endpoint = endPointDAO.save(endpoint);
			endPointDTO.setMessage("Endpoint inserido com sucesso.");
			endPointDTO.setSucesso(true);
		}
		catch (ConstraintViolationException e) {
			endPointDTO.setMessage(e.getConstraintViolations().iterator().next().getMessageTemplate());
		}
		catch(DataIntegrityViolationException e) {
			endPointDTO.setMessage("Campo único no banco de dados: "+ e.getMostSpecificCause());
		}

		return endPointDTO;
	}

	
	public List<Endpoint> pesquisarTodos() {
		return endPointDAO.findAll();
	}

	
	public List<Endpoint> pesquisarDescricao(String descricao) {
		return endPointDAO.findByDescricaoContainingIgnoreCase(descricao);
	}

}
