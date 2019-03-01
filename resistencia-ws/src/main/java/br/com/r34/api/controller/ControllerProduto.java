package br.com.r34.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.r34.negocio.domain.vo.produto.Produto;
import br.com.r34.negocio.service.produto.impl.ServiceProdutoImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Manutenção de produtos", value = "Manutenção de produtos")
@RestController
@RequestMapping("/produtos")
public class ControllerProduto {

	@Autowired
	private ServiceProdutoImpl serviceProdutoImpl;
	
		
	@ApiOperation(value = "listar produtos a venda", notes = "listar produtos a venda", protocols = "Accept=application/json", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class) })	
	@RequestMapping(value="/listar-produtos-vendas",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<List<Produto>> selectAll() {
		List<Produto> produtos = serviceProdutoImpl.listarProdutoVenda();
		
		return new ResponseEntity<List<Produto>>(produtos,HttpStatus.OK);
	}
	
		
	public void setServiceProdutoImpl(ServiceProdutoImpl serviceProdutoImpl) {
		this.serviceProdutoImpl = serviceProdutoImpl;
	}
}
