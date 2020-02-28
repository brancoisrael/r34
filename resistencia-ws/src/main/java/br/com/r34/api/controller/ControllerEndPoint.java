package br.com.r34.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.r34.persistencia.dto.acesso.EndPointDTO;
import br.com.r34.persistencia.vo.acesso.Endpoint;
import br.com.r34.service.acesso.ServiceEndPointImpl;
import br.com.r34.service.util.JWT;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Manutenção de endpoints", value = "Manutenção de endpoints")
@RestController
@RequestMapping("/endpoint")
public class ControllerEndPoint {
	
	@Autowired
	private ServiceEndPointImpl serviceEndPointImpl;
	
	@Autowired
	private HttpServletRequest request;
	
	@ApiOperation(value = "salvar", notes = "salvar", protocols = "Accept=application/json", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class) })	
	@RequestMapping(value="/salvar",method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public EndPointDTO salvar(@RequestBody Endpoint endpoint) {
		EndPointDTO endPointDTO = serviceEndPointImpl.inserir(endpoint);
		
		return endPointDTO;
	}
	
	@ApiOperation(value = "atualzar", notes = "atualzar", protocols = "Accept=application/json", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class) })	
	@RequestMapping(value="/atualizar",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public EndPointDTO atualizar(@RequestBody Endpoint endpoint) {
		EndPointDTO endPointDTO = serviceEndPointImpl.atualizar(endpoint);
		
		return endPointDTO;
	}
	
	@ApiOperation(value = "excluir", notes = "excluir", protocols = "Accept=application/json", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class) })	
	@RequestMapping(value="/excluir/{id}",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public EndPointDTO excluir(@PathVariable long id) {
		EndPointDTO endPointDTO = serviceEndPointImpl.deletar(id);
		
		return endPointDTO;
	}
	
	@ApiOperation(value = "pesquisar todos", notes = "pesquisar todos", protocols = "Accept=application/json", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class) })	
	@RequestMapping(value="/pesquisar-todos",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Endpoint> pesquisarTodos() {
		List<Endpoint> list = serviceEndPointImpl.pesquisarTodos();
		
		return list;
	}
	
	@ApiOperation(value = "pesquisar por descricao", notes = "pesquisar por descricao", protocols = "Accept=application/json", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class) })	
	@RequestMapping(value="/pesquisar-descricao/{descricao}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Endpoint> pesquisarDescricao(@PathVariable String descricao) {
		List<Endpoint> list = serviceEndPointImpl.pesquisarDescricao(descricao);
		
		return list;
	}
 
	@ApiOperation(value = "buscar menu membro", notes = "buscar menu membro", protocols = "Accept=application/json", response = EndPointDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = EndPointDTO.class) })	
	@RequestMapping(value="/buscar-menu-membro",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<EndPointDTO> buscarMenuMembro() {
		String email = JWT.recuperarLogin(request.getHeader("authorization"));
		List<EndPointDTO> list = serviceEndPointImpl.buscarMenuMembro(email);
		
		return list;
	}
}
