package com.fpsoluctionstechs.hortfruitonline.controller.produtoPedido.response;

import java.math.BigDecimal;
import com.fpsoluctionstechs.hortfruitonline.controller.produto.response.ProdutoResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProdutoPedidoResponse {	
	private Long id;
	private ProdutoResponse produto;
	private int quantidade;
	private BigDecimal unidadeMedidaGrama;
	private BigDecimal precoUnidade;
	private BigDecimal precoTotal;
	private BigDecimal totalMedidaGrama;
}
