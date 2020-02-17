package br.com.r34.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.r34.persistencia.dto.membro.MembroDTO;
import br.com.r34.persistencia.vo.membro.Membro;
import br.com.r34.service.membro.ServiceMembroImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Manutenção de membros", value = "Manutenção de membros")
@RestController
@RequestMapping("/membros")
public class ControllerMembro {

	@Autowired
	private ServiceMembroImpl serviceMembroImpl;
	
	@ApiOperation(value = "salvar", notes = "salvar", protocols = "Accept=application/json", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class) })	
	@RequestMapping(value="/salvar",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<MembroDTO> salvar(@RequestBody Membro membro) {
		MembroDTO membroDTO = serviceMembroImpl.inserir(membro);
		
		return new ResponseEntity<MembroDTO>(membroDTO,HttpStatus.OK);
	}

	@ApiOperation(value = "atualizar", notes = "atualizar", protocols = "Accept=application/json", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class) })	
	@RequestMapping(value="/atualizar",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<MembroDTO> atualizar(@RequestBody Membro membro) {
		MembroDTO membroDTO = serviceMembroImpl.atualizar(membro);
		
		return new ResponseEntity<MembroDTO>(membroDTO,HttpStatus.OK);
	}
	
	@ApiOperation(value = "buscar todos", notes = "buscar todos", protocols = "Accept=application/json", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class) })	
	@RequestMapping(value="/buscartodos",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Iterable<Membro>> selectAll() {
		Iterable<Membro> membros = serviceMembroImpl.selectAll();
		
		return new ResponseEntity<Iterable<Membro>>(membros,HttpStatus.OK);
	}
		
	@ApiOperation(value = "excluir", notes = "excluir", protocols = "Accept=application/json", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class) })	
	@RequestMapping(value="/excluir/{id}",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<MembroDTO> excluir(@PathVariable long id) {
		MembroDTO membroDTO = serviceMembroImpl.deletar(id);
		
		return new ResponseEntity<MembroDTO>(membroDTO,HttpStatus.OK);
	}
	
	@CrossOrigin
	@ApiOperation(value = "pesquisar por id", notes = "pesquisar por id", protocols = "Accept=application/json", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class) })	
	@RequestMapping(value="/buscarid/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Membro> buscarId(@PathVariable long id) {
		Membro membro = serviceMembroImpl.buscarId(id);
		
		return new ResponseEntity<Membro>(membro,HttpStatus.OK);
	}
	
	@CrossOrigin
	@ApiOperation(value = "pesquisar por status", notes = "pesquisar por status", protocols = "Accept=application/json", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class) })	
	@RequestMapping(value="/buscar-status/{status}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<List<Membro>> buscarByStatus(@PathVariable boolean status) {
		List<Membro> membros = serviceMembroImpl.selectByStatus(status);
		
		return new ResponseEntity<List<Membro>>(membros,HttpStatus.OK);
	}
	
	public void setServiceMembroImpl(ServiceMembroImpl serviceMembroImpl) {
		this.serviceMembroImpl = serviceMembroImpl;
	}
}
