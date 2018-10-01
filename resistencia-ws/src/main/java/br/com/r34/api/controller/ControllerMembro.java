package br.com.r34.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.r34.negocio.domain.dto.membro.MembroDTO;
import br.com.r34.negocio.domain.vo.membro.Membro;
import br.com.r34.negocio.service.membro.impl.ServiceMembroImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "membro", value = "membro")
@RestController
public class ControllerMembro {

	@Autowired
	private ServiceMembroImpl serviceMembroImpl;
	
	@ApiOperation(value = "salvar", notes = "salvar", protocols = "Accept=application/json", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class) })	
	@RequestMapping(value="/salvar",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public MembroDTO salvar(@RequestBody Membro membro) {
		MembroDTO membroDTO = serviceMembroImpl.inserir(membro);
		
		return membroDTO;
	}

	
	public void setServiceMembroImpl(ServiceMembroImpl serviceMembroImpl) {
		this.serviceMembroImpl = serviceMembroImpl;
	}
}
