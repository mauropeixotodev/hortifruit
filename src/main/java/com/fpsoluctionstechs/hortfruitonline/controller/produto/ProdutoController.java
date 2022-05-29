package com.fpsoluctionstechs.hortfruitonline.controller.produto;

import java.net.URI;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fpsoluctionstechs.hortfruitonline.controller.produto.request.AtualizacaoProdutoRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.produto.request.ProdutoRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.produto.response.ProdutoResponse;
import com.fpsoluctionstechs.hortfruitonline.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	@Autowired
	ProdutoService produtoService;

	@PostMapping("")
	public ResponseEntity<ProdutoResponse> cadastro(@RequestBody @Validated ProdutoRequest ProdutoRequest,
			UriComponentsBuilder uriBuilder) throws Exception {
		ProdutoResponse produto = produtoService.salvarProduto(ProdutoRequest);
		URI uri = uriBuilder.path("/produto/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(produto);

	}
	@GetMapping("")
	public List<ProdutoResponse> listar() {
		return produtoService.listarProdutos();
	}
	@PutMapping("")
	public ResponseEntity<ProdutoResponse> atualizacao(
			@RequestBody @Validated AtualizacaoProdutoRequest atualizacaoProdutoRequest) throws Exception {
		try {
			return ResponseEntity.ok(produtoService.Atualizar(atualizacaoProdutoRequest));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	
	
	
	
	
	
	

}
