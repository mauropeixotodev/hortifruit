package com.fpsoluctionstechs.hortfruitonline.controller.pedido.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatusPedidoResponse {

	private String key;
	private String nome;
	private String descricao;
	private String iconName;
}
