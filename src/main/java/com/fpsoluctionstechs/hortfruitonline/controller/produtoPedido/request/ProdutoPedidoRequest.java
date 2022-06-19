package com.fpsoluctionstechs.hortfruitonline.controller.produtoPedido.request;

import com.fpsoluctionstechs.hortfruitonline.controller.medida.request.MedidaIdRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.produto.request.ProdutoIdRequest;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ProdutoPedidoRequest {
	
	@NotNull
	private ProdutoIdRequest produto;
	@NotNull
	private int quantidade;
	@NotNull
	private MedidaIdRequest medida;
}
