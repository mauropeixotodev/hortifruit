package com.fpsoluctionstechs.hortfruitonline.controller.pedido.request;


import java.util.List;

import org.springframework.lang.NonNull;

import com.fpsoluctionstechs.hortfruitonline.controller.endereco.request.EnderecoRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.produtoPedido.request.ProdutoPedidoRequest;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PedidoRequest {
	
	
	@NonNull
	private List<ProdutoPedidoRequest> produtoPedidos;
	@NonNull
	private EnderecoRequest endereco;
	@NonNull
	private String contato;
	@NonNull
	private String cliente;
}
