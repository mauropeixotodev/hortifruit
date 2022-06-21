package com.fpsoluctionstechs.hortfruitonline.controller.pedido.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProdutosPedidosRelatorioResponse {

	private String produto;
	private String medida;
	private BigDecimal quantidade;
	private BigDecimal preco;
}
