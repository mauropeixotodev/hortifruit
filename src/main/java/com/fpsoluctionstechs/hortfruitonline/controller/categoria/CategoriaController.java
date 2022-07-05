package com.fpsoluctionstechs.hortfruitonline.controller.categoria;

import java.net.URI;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fpsoluctionstechs.hortfruitonline.controller.categoria.request.AtualizarCategoriaRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.categoria.request.CategoriaRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.categoria.response.CategoriaResponse;
import com.fpsoluctionstechs.hortfruitonline.controller.categoria.response.CategoriaResponseGet;
import com.fpsoluctionstechs.hortfruitonline.service.CategoriaService;

@RestController
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;

	@PostMapping("/admin/categoria")
	public ResponseEntity<CategoriaResponse> cadastro(@RequestBody @Valid CategoriaRequest categoriaRequest,
			UriComponentsBuilder uriBuilder) throws Exception {
		CategoriaResponse categoriaResponse = categoriaService.salvar(categoriaRequest);
		URI uri = uriBuilder.path("/categoria/{id}").buildAndExpand(categoriaResponse.getId()).toUri();
		return ResponseEntity.created(uri).body(categoriaResponse);
	}

	@PutMapping("/admin/categoria")
	public ResponseEntity<CategoriaResponse> atualizacao(
			@RequestBody @Valid  AtualizarCategoriaRequest atualizarCategoriaRequest) throws Exception {
		try {
			return ResponseEntity.ok(categoriaService.atualizarCategoria(atualizarCategoriaRequest));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("/categoria")
	public List<CategoriaResponseGet> listar() {
		return categoriaService.buscarCategorias();
	}

	@GetMapping("/admin/categoria")
	public List<CategoriaResponseGet> listarAdmin() {
		return categoriaService.buscarCategorias();
	}

	@DeleteMapping("/admin/categoria/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) throws Exception {
		try {
			categoriaService.deletarCategoria(id);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().build();
	}

}
