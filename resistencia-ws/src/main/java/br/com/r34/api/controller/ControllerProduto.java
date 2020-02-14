package br.com.r34.api.controller;

import java.util.List;

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

import br.com.r34.persistencia.dto.produto.ProdutoVendaDTO;
import br.com.r34.persistencia.vo.produto.Produto;
import br.com.r34.persistencia.vo.produto.ProdutoVenda;
import br.com.r34.persistencia.vo.produto.TipoProduto;
import br.com.r34.service.produto.ServiceProdutoImpl;
import br.com.r34.service.produto.ServiceTipoProdutoImpl;
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
	
	@Autowired
	private ServiceTipoProdutoImpl serviceTipoProdutoImpl;
		
	@ApiOperation(value = "listar produtos a venda", notes = "listar produtos a venda", protocols = "Accept=application/json", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class) })	
	@RequestMapping(value="/listar-produtos-vendas/{idTipoProduto}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<List<Produto>> selectProdutoVendaByTipo(@PathVariable long idTipoProduto) {
		List<Produto> produtos = serviceProdutoImpl.listarProdutoVenda(idTipoProduto);
		
		return new ResponseEntity<>(produtos,HttpStatus.OK);
	}
	
	@ApiOperation(value = "buscar produto venda por data", notes = "buscar produto venda por data", protocols = "Accept=application/json", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class) })	
	@RequestMapping(value="/buscar-produto-venda-data",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<ProdutoVenda> selectProdutoVendaByData(@RequestBody ProdutoVendaDTO produtoVendaDTO){
		ProdutoVenda produtoVenda = serviceProdutoImpl.buscarPorProdutoData(produtoVendaDTO);
		
		return new ResponseEntity<>(produtoVenda,HttpStatus.OK);
	}
	
	@ApiOperation(value = "listar tipo de produto", notes = "listar tipo de produto", protocols = "Accept=application/json", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class) })	
	@RequestMapping(value="/listar-tipo-produto",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<List<TipoProduto>> selectTipoProdutoAll() {
		List<TipoProduto> tipoProdutos = serviceTipoProdutoImpl.listarTipoProduto();
		
		return new ResponseEntity<List<TipoProduto>>(tipoProdutos,HttpStatus.OK);
	}
		
	public void setServiceProdutoImpl(ServiceProdutoImpl serviceProdutoImpl) {
		this.serviceProdutoImpl = serviceProdutoImpl;
	}
}
