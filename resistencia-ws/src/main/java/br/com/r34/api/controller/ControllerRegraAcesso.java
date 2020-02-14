package br.com.r34.api.controller;

import java.util.List;

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

import br.com.r34.persistencia.dto.acesso.RegraAcessoDTO;
import br.com.r34.persistencia.vo.acesso.RegraAcesso;
import br.com.r34.service.acesso.ServiceRegraAcessoImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Manutenção de regra de acesso", value = "Manutenção de regra de acesso")
@RestController
public class ControllerRegraAcesso {

	@Autowired
	private ServiceRegraAcessoImpl serviceRegraAcessoImpl;
	
	@ApiOperation(value = "salvar", notes = "salvar", protocols = "Accept=application/json", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class) })	
	@RequestMapping(value="/salvarregraacesso",method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public RegraAcessoDTO salvar(@RequestBody RegraAcesso regraAcesso) {
		RegraAcessoDTO regraAcessoDTO = serviceRegraAcessoImpl.inserir(regraAcesso);
		
		return regraAcessoDTO;
	}
	
	@ApiOperation(value = "atualzar", notes = "atualzar", protocols = "Accept=application/json", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class) })	
	@RequestMapping(value="/atualizarregraacesso",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public RegraAcessoDTO atualizar(@RequestBody RegraAcesso regraAcesso) {
		RegraAcessoDTO regraAcessoDTO = serviceRegraAcessoImpl.atualizar(regraAcesso);
		
		return regraAcessoDTO;
	}
	
	@ApiOperation(value = "excluir", notes = "excluir", protocols = "Accept=application/json", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class) })	
	@RequestMapping(value="/excluirregraacesso/{id}",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public RegraAcessoDTO excluir(@PathVariable long id) {
		RegraAcessoDTO regraAcessoDTO = serviceRegraAcessoImpl.deletar(id);
		
		return regraAcessoDTO;
	}
	
	@ApiOperation(value = "pesquisar todos", notes = "pesquisar todos", protocols = "Accept=application/json", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class) })	
	@RequestMapping(value="/pesquisartodosregraacesso",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<RegraAcesso> pesquisarTodos() {
		List<RegraAcesso> list = serviceRegraAcessoImpl.pesquisarTodos();
		
		return list;
	}
	
}
