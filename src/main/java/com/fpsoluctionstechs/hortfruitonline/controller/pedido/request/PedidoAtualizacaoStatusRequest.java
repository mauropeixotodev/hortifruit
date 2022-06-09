package com.fpsoluctionstechs.hortfruitonline.controller.pedido.request;


import org.springframework.lang.NonNull;

import com.fpsoluctionstechs.hortfruitonline.enums.StatusPedido;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PedidoAtualizacaoStatusRequest {
	@NonNull
	private Long id;
	@NonNull
	private StatusPedido status;

}
