package com.fpsoluctionstechs.hortfruitonline.controller.pedido.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class PedidoIdRequest {
	@NotNull
	private Long id;

}
