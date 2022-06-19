package com.fpsoluctionstechs.hortfruitonline.controller.pedido.request;

import java.util.List;
import com.fpsoluctionstechs.hortfruitonline.controller.endereco.request.EnderecoRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.produtoPedido.request.ProdutoPedidoRequest;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class PedidoRequest {
	
	
	@NotNull
	private List<ProdutoPedidoRequest> produtoPedidos;
	@NotNull
	private EnderecoRequest endereco;
	@NotNull
	private String contato;
	@NotNull
	private String cliente;
}
