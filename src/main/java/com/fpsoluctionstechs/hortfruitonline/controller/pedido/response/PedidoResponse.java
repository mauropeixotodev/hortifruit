package com.fpsoluctionstechs.hortfruitonline.controller.pedido.response;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;



import com.fpsoluctionstechs.hortfruitonline.controller.endereco.response.EnderecoResponse;
import com.fpsoluctionstechs.hortfruitonline.controller.produtoPedido.response.ProdutoPedidoResponse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PedidoResponse {

	private Long id;
	private List<ProdutoPedidoResponse> produtosPedidos;
	private EnderecoResponse endereco;
	private String contato;
	private String cliente;
	private BigDecimal valorTotal;
	private String status;
    private LocalDate dataCriacao;
    private LocalDate dataAtualizacao;
}
