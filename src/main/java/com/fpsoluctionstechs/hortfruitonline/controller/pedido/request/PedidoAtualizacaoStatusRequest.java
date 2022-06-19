package com.fpsoluctionstechs.hortfruitonline.controller.pedido.request;

import com.fpsoluctionstechs.hortfruitonline.enums.StatusPedido;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class PedidoAtualizacaoStatusRequest {
	@NotNull
	private Long id;
	@NotNull
	private StatusPedido status;

}
