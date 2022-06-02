package com.fpsoluctionstechs.hortfruitonline.controller.produtoPedido.request;



import org.springframework.lang.NonNull;

import com.fpsoluctionstechs.hortfruitonline.controller.medida.request.MedidaIdRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.produto.request.ProdutoIdRequest;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProdutoPedidoRequest {
	
	@NonNull
	private ProdutoIdRequest produto;
	@NonNull
	private int quantidade;
	@NonNull
	private MedidaIdRequest medida;
}
