package com.fpsoluctionstechs.hortfruitonline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpsoluctionstechs.hortfruitonline.controller.endereco.request.EnderecoRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.endereco.response.EnderecoResponse;
import com.fpsoluctionstechs.hortfruitonline.controller.pedido.request.PedidoRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.pedido.response.PedidoResponse;
import com.fpsoluctionstechs.hortfruitonline.controller.produtoPedido.request.ProdutoPedidoRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.produtoPedido.response.ProdutoPedidoResponse;
import com.fpsoluctionstechs.hortfruitonline.model.*;
import com.fpsoluctionstechs.hortfruitonline.respository.PedidoRepository;
import com.fpsoluctionstechs.hortfruitonline.respository.ProdutoRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	ProdutoService produtoService;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<PedidoResponse> listar(){
		return builderPedidoResponse(pedidoRepository.findAll());
	}
	
	
	
	public PedidoResponse salvarPedido(PedidoRequest pedidoRequest) {
		
		return builderPedidoResponse(pedidoRepository.save(builderPedido(pedidoRequest)));
	}
	
	private List<PedidoResponse> builderPedidoResponse(List<Pedido> pedidos) {
		return pedidos.stream().map(pedido -> builderPedidoResponse(pedido)).collect(Collectors.toList());
		
	}
	
	
	private PedidoResponse builderPedidoResponse(Pedido pedido) {
		
		return PedidoResponse.builder()
				.cliente(pedido.getCliente())
				.contato(pedido.getContato())
				.endereco(builderEnderecoResponse(pedido.getEndereco()))
				.id(pedido.getId())
				.valorTotal(pedido.getValorTotal())
				.produtosPedidos(builderProdutoPedidoResponse(pedido.getProdutoPedidos()))
				.build();
		
		
		
	}
	
	private List<ProdutoPedidoResponse> builderProdutoPedidoResponse(List<ProdutoPedido> produtoPedidos){
		return produtoPedidos.stream().map(produtoPedido -> builderProdutoPedidoResponse(produtoPedido)).collect(Collectors.toList());
		
	}
	
	private ProdutoPedidoResponse builderProdutoPedidoResponse(ProdutoPedido produtoPedido ) {
		
		return ProdutoPedidoResponse.builder()
				.id(produtoPedido.getId())
				.precoTotal(produtoPedido.getPrecoTotal())
				.precoUnidade(produtoPedido.getPrecoUnidade())
				.quantidade(produtoPedido.getQuantidade())
				.totalMedidaGrama(produtoPedido.getTotalMedidaGrama())
				.unidadeMedidaGrama(produtoPedido.getUnidadeMedidaGrama())
				.produto(produtoService.builderProdutoResponse(produtoPedido.getProduto()))
				.build();
		
		
	}
	
	private EnderecoResponse builderEnderecoResponse(Endereco endereco) {
		
		return EnderecoResponse.builder()
				.bairro(endereco.getBairro())
				.complemento(endereco.getComplemento())
				.logradouro(endereco.getLogradouro())
				.numero(endereco.getNumero())
				.referencia(endereco.getReferencia())
				.latitude(endereco.getLatitude())
				.longitude(endereco.getLongitude())
				.id(endereco.getId())
				.build();
		
	}
	
	

	private Pedido builderPedido(PedidoRequest pedidoRequest) {
	Pedido pedido =	Pedido.builder()
		.cliente(pedidoRequest.getCliente())
		.contato(pedidoRequest.getContato())
		.endereco(builderEndereco(pedidoRequest.getEndereco()))
		.build();
		pedido.setProdutoPedidos(builderProdutoPedido(pedidoRequest.getProdutoPedidos(), pedido ));
		pedido.setValorTotal(pedido.getProdutoPedidos().stream().map(produtoPedido -> produtoPedido.getPrecoTotal()).reduce(BigDecimal.valueOf(0), BigDecimal::add));
		return pedido;

	}
	
	
	private List<ProdutoPedido> builderProdutoPedido(List<ProdutoPedidoRequest> produtoPedidoRequests, Pedido pedido){
		
		return produtoPedidoRequests.stream().map(produtoPedidoRequest -> builderProdutoPedido(produtoPedidoRequest, pedido)).collect(Collectors.toList());
		
	}
	
	private ProdutoPedido builderProdutoPedido(ProdutoPedidoRequest produtoPedidoRequest, Pedido pedido ) {
		Produto produto = builderProduto(produtoPedidoRequest);
		List<Medida> medidas =  produto.getMedidas().stream().filter(medida -> medida.getId() == produtoPedidoRequest.getMedida().getId()).collect(Collectors.toList());
		Medida medida = medidas.get(0);
		return ProdutoPedido.builder()
				.pedido(pedido)
				.unidadeMedidaGrama(medida.getUnidadeEmGramas())
				.produto(produto)
				.quantidade(produtoPedidoRequest.getQuantidade())
				.precoUnidade(medida.getPreco())
				.precoTotal(medida.getPreco().multiply(BigDecimal.valueOf(produtoPedidoRequest.getQuantidade())))
				.totalMedidaGrama(medida.getUnidadeEmGramas().multiply(BigDecimal.valueOf(produtoPedidoRequest.getQuantidade())))
				.build();
	}
	
	private Produto builderProduto(ProdutoPedidoRequest produtoPedidoRequest) {
	Optional<Produto> optional = produtoRepository.findById(produtoPedidoRequest.getProduto().getId());
	if(optional.isPresent()) {
		return optional.get();
	}

	throw new EntityNotFoundException("Produto não encontrado");
		
	}
	
	
	private Endereco builderEndereco(EnderecoRequest enderecoRequest) {
		return Endereco.builder()
				.bairro(enderecoRequest.getBairro())
				.complemento(enderecoRequest.getComplemento())
				.logradouro(enderecoRequest.getLogradouro())
				.latitude(enderecoRequest.getLatitude())
				.longitude(enderecoRequest.getLongitude())
				.numero(enderecoRequest.getNumero())
				.referencia(enderecoRequest.getReferencia())
				.build();
	}
	
	
	
	
	
	
	
	

}
