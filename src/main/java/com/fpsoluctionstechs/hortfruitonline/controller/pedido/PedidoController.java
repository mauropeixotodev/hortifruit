   package com.fpsoluctionstechs.hortfruitonline.controller.pedido;

import java.net.URI;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import com.fpsoluctionstechs.hortfruitonline.controller.pedido.response.ProdutosPedidosRelatorioResponse;
import com.fpsoluctionstechs.hortfruitonline.controller.pedido.response.StatusPedidoResponse;
import com.fpsoluctionstechs.hortfruitonline.enums.StatusPedido;
import com.fpsoluctionstechs.hortfruitonline.respository.relatorio.PedidosProdutosRelatorio;
import com.fpsoluctionstechs.hortfruitonline.service.relatorio.PedidosProdutosRelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fpsoluctionstechs.hortfruitonline.controller.pedido.request.PedidoAtualizacaoStatusRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.pedido.request.PedidoIdRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.pedido.request.PedidoRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.pedido.response.PedidoResponse;
import com.fpsoluctionstechs.hortfruitonline.service.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private PedidosProdutosRelatorioService pedidosProdutosRelatorioService;

	@PostMapping("")
	public ResponseEntity<PedidoResponse> cadastro(@RequestBody @Valid PedidoRequest pedidoRequest, UriComponentsBuilder uriBuilder){
		PedidoResponse pedido = pedidoService.salvarPedido(pedidoRequest);
		URI uri = uriBuilder.path("/pedido/{id}").buildAndExpand(pedido.getId()).toUri();
		return ResponseEntity.created(uri).body(pedido);
	}

	@GetMapping("")
	public List<PedidoResponse> listar() {
		return pedidoService.listar();
	}

	@PostMapping("/id")
	public ResponseEntity<PedidoResponse> buscar(@RequestBody @Valid PedidoIdRequest pedidoIdRequest){
		try {
			return ResponseEntity.ok(pedidoService.buscarPedido(pedidoIdRequest));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	
	}

	@PutMapping("")
	public ResponseEntity<PedidoResponse> atualizacao(@RequestBody @Valid PedidoAtualizacaoStatusRequest pedidoAtualizacaoStatusRequest) {
		try {
			return ResponseEntity.ok(pedidoService.atualizarStatusPedido(pedidoAtualizacaoStatusRequest));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/relatorio")
	public List<ProdutosPedidosRelatorioResponse> relatorio() {
		return pedidosProdutosRelatorioService.filtrarProdutosPedidosByStatus(StatusPedido.AGUARDANDO_PROCESSAMENTO);
	}

	@GetMapping("/status")
	public List<StatusPedidoResponse> status() {
		return pedidoService.status();
	}

}
