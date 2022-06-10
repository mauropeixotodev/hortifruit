package com.fpsoluctionstechs.hortfruitonline.controller.pedido.request;



import org.springframework.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PedidoIdRequest {
	@NonNull
	private Long id;

}
