package br.com.r34.negocio.service.acesso.impl;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.r34.negocio.dao.acesso.EndPointDAO;
import br.com.r34.negocio.domain.dto.acesso.EndPointDTO;
import br.com.r34.negocio.domain.vo.acesso.Endpoint;
import br.com.r34.negocio.service.acesso.ServiceEndPoint;

@Service
public class ServiceEndPointImpl implements ServiceEndPoint {

	@Autowired
	private EndPointDAO endPointDAO;

	@Override
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

	@Override
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

	@Override
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

	@Override
	public List<Endpoint> pesquisarTodos() {
		return endPointDAO.findAll();
	}

	@Override
	public List<Endpoint> pesquisarDescricao(String descricao) {
		return endPointDAO.findByDescricaoContainingIgnoreCase(descricao);
	}

}
