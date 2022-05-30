package com.fpsoluctionstechs.hortfruitonline.controller.pedido;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


import com.fpsoluctionstechs.hortfruitonline.controller.pedido.request.PedidoRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.pedido.response.PedidoResponse;
import com.fpsoluctionstechs.hortfruitonline.service.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	PedidoService PedidoService;

	@PostMapping("")
	public ResponseEntity<PedidoResponse> cadastro(@RequestBody @Validated PedidoRequest pedidoRequest, UriComponentsBuilder uriBuilder) throws Exception {
		PedidoResponse pedido = PedidoService.salvarPedido(pedidoRequest);
		URI uri = uriBuilder.path("/pedido/{id}").buildAndExpand(pedido.getId()).toUri();
		return ResponseEntity.created(uri).body(pedido);
	}
	

	@GetMapping("")
	public List<PedidoResponse> listar() {
		return PedidoService.listar();
	}
	
	
	
	

}
