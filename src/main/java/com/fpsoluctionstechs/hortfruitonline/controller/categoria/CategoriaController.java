package com.fpsoluctionstechs.hortfruitonline.controller.categoria;

import java.net.URI;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fpsoluctionstechs.hortfruitonline.controller.categoria.request.AtualizarCategoriaRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.categoria.request.CategoriaRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.categoria.response.CategoriaResponse;
import com.fpsoluctionstechs.hortfruitonline.controller.categoria.response.CategoriaResponseGet;
import com.fpsoluctionstechs.hortfruitonline.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;

	@PostMapping("")
	public ResponseEntity<CategoriaResponse> cadastro(@RequestBody @Validated CategoriaRequest categoriaRequest,
			UriComponentsBuilder uriBuilder) throws Exception {
		CategoriaResponse categoriaResponse = categoriaService.salvar(categoriaRequest);
		URI uri = uriBuilder.path("/categoria/{id}").buildAndExpand(categoriaResponse.getId()).toUri();
		return ResponseEntity.created(uri).body(categoriaResponse);
	}

	@PutMapping("")
	public ResponseEntity<CategoriaResponse> atualizacao(
			@RequestBody @Validated AtualizarCategoriaRequest atualizarCategoriaRequest) throws Exception {
		try {
			return ResponseEntity.ok(categoriaService.atualizarCategoria(atualizarCategoriaRequest));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("")
	public List<CategoriaResponseGet> listar() {
		return categoriaService.buscarCategorias();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) throws Exception {
		try {
			categoriaService.deletarCategoria(id);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().build();
	}

}
