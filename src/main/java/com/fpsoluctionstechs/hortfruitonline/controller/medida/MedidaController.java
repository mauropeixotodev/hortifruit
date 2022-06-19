package com.fpsoluctionstechs.hortfruitonline.controller.medida;

import com.fpsoluctionstechs.hortfruitonline.controller.medida.request.AtualizarMedidaRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.medida.request.MedidaRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.medida.request.MedidaResponseGet;
import com.fpsoluctionstechs.hortfruitonline.controller.medida.response.MedidaResponse;
import com.fpsoluctionstechs.hortfruitonline.controller.produto.request.AtualizacaoProdutoRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.produto.request.ProdutoRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.produto.response.ProdutoResponse;
import com.fpsoluctionstechs.hortfruitonline.service.MedidaService;
import com.fpsoluctionstechs.hortfruitonline.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/medida")
public class MedidaController {
	@Autowired
	private MedidaService medidaService;

	@PostMapping("")
	public ResponseEntity<MedidaResponse> cadastro(@RequestBody @Validated MedidaRequest medidaRequest,
												   UriComponentsBuilder uriBuilder) throws Exception {
		MedidaResponse medidaResponse = medidaService.salvar(medidaRequest);
		URI uri = uriBuilder.path("/medida/{id}").buildAndExpand(medidaResponse.getId()).toUri();
		return ResponseEntity.created(uri).body(medidaResponse);

	}
	@GetMapping("")
	public List<MedidaResponseGet> listar() {
		return medidaService.listar();
	}
	
	@PutMapping("")
	public ResponseEntity<MedidaResponse> atualizacao(
			@RequestBody @Validated AtualizarMedidaRequest atualizarMedidaRequest) throws Exception {
		try {
			return ResponseEntity.ok(medidaService.atualizar(atualizarMedidaRequest));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	
	
	
	
	
	
	

}
