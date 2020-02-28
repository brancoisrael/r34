package br.com.r34.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.r34.persistencia.dto.lancamento.LancamentoDTO;
import br.com.r34.persistencia.vo.lancamento.Lancamento;
import br.com.r34.service.lancamento.ServiceLancamentoImpl;
import br.com.r34.service.membro.ServiceMembroImpl;
import br.com.r34.service.util.JWT;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Manutenção de lançamentos", value = "Manutenção de lançamentos")
@RestController
@RequestMapping("/lancamentos")
public class ControllerLancamento {

	@Autowired
	private ServiceLancamentoImpl serviceLancamentoImpl;
	
	@Autowired
	private ServiceMembroImpl serviceMembroImpl;
	
	@Autowired
	private HttpServletRequest request;
	
	@ApiOperation(value = "salvar", notes = "salvar", protocols = "Accept=application/json", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class) })	
	@RequestMapping(value="/salvar",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<LancamentoDTO> salvar(@RequestBody Lancamento lancamento) {
		LancamentoDTO lancamentoDTO = serviceLancamentoImpl.inserir(lancamento);
		lancamentoDTO.setSaldoMembro(serviceMembroImpl.selectSaldoByMembro(lancamento.getMembro().getId()));
		
		return new ResponseEntity<LancamentoDTO>(lancamentoDTO,HttpStatus.OK);
	}	

	@ApiOperation(value = "excluir", notes = "excluir", protocols = "Accept=application/json", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class) })	
	@RequestMapping(value="/excluir",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<LancamentoDTO> excluir(@RequestBody Lancamento lancamento) {
		LancamentoDTO lancamentoDTO = serviceLancamentoImpl.deletar(lancamento);
		lancamentoDTO.setSaldoMembro(serviceMembroImpl.selectSaldoByMembro(lancamento.getMembro().getId()));
		
		return new ResponseEntity<LancamentoDTO>(lancamentoDTO,HttpStatus.OK);
	}	

	@ApiOperation(value = "atualizar", notes = "atualizar", protocols = "Accept=application/json", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class) })	
	@RequestMapping(value="/atualizar",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<LancamentoDTO> atualziar(@RequestBody Lancamento lancamento) {
		LancamentoDTO lancamentoDTO = serviceLancamentoImpl.atualizar(lancamento);
		
		return new ResponseEntity<LancamentoDTO>(lancamentoDTO,HttpStatus.OK);
	}
	
	@ApiOperation(value = "pesquisar lancamento por membro", notes = "pesquisar lancamento por membro", protocols = "Accept=application/json", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class) })	
	@RequestMapping(value="/pesquisar-membro/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<List<Lancamento>> pesquisarByMembro(@PathVariable long id) {
		List<Lancamento> lancamentos = serviceLancamentoImpl.pesquisarPorMembro(id);
		
		return new ResponseEntity<List<Lancamento>>(lancamentos,HttpStatus.OK);
	}
	
	@ApiOperation(value = "pesquisar lancamento do membro", notes = "pesquisar lancamento do membro", protocols = "Accept=application/json", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class) })	
	@RequestMapping(value="/membro",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<List<Lancamento>> pesquisarMembro() {
		List<Lancamento> lancamentos = serviceLancamentoImpl.pesquisarPorMembro(JWT.recuperarLogin(request.getHeader("authorization")));		
		return new ResponseEntity<List<Lancamento>>(lancamentos,HttpStatus.OK);
	}
	
}
