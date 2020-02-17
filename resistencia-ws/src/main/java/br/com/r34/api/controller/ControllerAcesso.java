package br.com.r34.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.r34.persistencia.dto.acesso.LoginDTO;
import br.com.r34.service.acesso.ServiceLogin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de acesso", value = "Controller de acesso")
@RestController
@RequestMapping("/acesso")
public class ControllerAcesso {

	@Autowired
	private ServiceLogin serviceLogin;
	
	@ApiOperation(value = "login", notes = "login", protocols = "Accept=application/json", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class) })	
	@RequestMapping(value="/login",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<String> login(@RequestBody LoginDTO login) {
		String token = serviceLogin.login(login.getEmail(), login.getSenha());
		return new ResponseEntity<String>(token,HttpStatus.OK);
	}
}
