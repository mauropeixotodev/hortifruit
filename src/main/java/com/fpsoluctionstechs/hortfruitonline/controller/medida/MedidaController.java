package com.fpsoluctionstechs.hortfruitonline.controller.medida;

import com.fpsoluctionstechs.hortfruitonline.controller.medida.request.AtualizarMedidaRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.medida.request.MedidaRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.medida.request.MedidaResponseGet;
import com.fpsoluctionstechs.hortfruitonline.controller.medida.response.MedidaResponse;
import com.fpsoluctionstechs.hortfruitonline.service.MedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import java.net.URI;
import java.util.List;

@RestController
public class MedidaController {
	@Autowired
	private MedidaService medidaService;

	@PostMapping("/admin/medida")
	public ResponseEntity<MedidaResponse> cadastro(@RequestBody @Valid  MedidaRequest medidaRequest,
												   UriComponentsBuilder uriBuilder) throws Exception {
		MedidaResponse medidaResponse = medidaService.salvar(medidaRequest);
		URI uri = uriBuilder.path("/medida/{id}").buildAndExpand(medidaResponse.getId()).toUri();
		return ResponseEntity.created(uri).body(medidaResponse);

	}
	@GetMapping("/admin/medida")
	public List<MedidaResponseGet> listar() {
		return medidaService.listar();
	}
	
	@PutMapping("/admin/medida")
	public ResponseEntity<MedidaResponse> atualizacao(
			@RequestBody @Validated AtualizarMedidaRequest atualizarMedidaRequest) throws Exception {
		try {
			return ResponseEntity.ok(medidaService.atualizar(atualizarMedidaRequest));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	
	
	
	
	
	
	

}
