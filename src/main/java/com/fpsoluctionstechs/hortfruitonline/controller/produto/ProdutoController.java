package com.fpsoluctionstechs.hortfruitonline.controller.produto;

import java.net.URI;
import java.util.List;

import javax.persistence.EntityNotFoundException;


import com.fpsoluctionstechs.hortfruitonline.controller.produto.response.EStatusProdutoResponse;
import com.fpsoluctionstechs.hortfruitonline.enums.EStatusProduto;
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
import com.fpsoluctionstechs.hortfruitonline.controller.produto.request.ProdutoIdRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.produto.request.ProdutoRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.produto.response.ProdutoResponse;
import com.fpsoluctionstechs.hortfruitonline.service.ProdutoService;

@RestController
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@PostMapping("/produto")
	public ResponseEntity<ProdutoResponse> cadastro(@Validated  @RequestBody ProdutoRequest ProdutoRequest,
			UriComponentsBuilder uriBuilder) {
		ProdutoResponse produto = produtoService.salvarProduto(ProdutoRequest);
		URI uri = uriBuilder.path("/produto/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(produto);

	}

	@GetMapping("/produto")
	public List<ProdutoResponse> listar() {
		return produtoService.listarProdutos();
	}

	@GetMapping("/admin/produto")
	public List<ProdutoResponse> listarProdutoAdmin() {
		return produtoService.listarProdutosAdmin();
	}

	@GetMapping("/admin/produto/status")
	public List<EStatusProdutoResponse> listarStatusProduto() {
		return produtoService.listarStatusProduto();
	}

	@PostMapping("/produto/id")
	public ResponseEntity<ProdutoResponse> buscarID(@Validated  @RequestBody ProdutoIdRequest produtoIdRequest) {
		try {
			ProdutoResponse produtoResponse = produtoService.buscarById(produtoIdRequest);
			return ResponseEntity.ok(produtoResponse);		
		
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/produto")
	public ResponseEntity<ProdutoResponse> atualizacao(@RequestBody @Validated  AtualizacaoProdutoRequest atualizacaoProdutoRequest) {
		try {
			return ResponseEntity.ok(produtoService.Atualizar(atualizacaoProdutoRequest));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
