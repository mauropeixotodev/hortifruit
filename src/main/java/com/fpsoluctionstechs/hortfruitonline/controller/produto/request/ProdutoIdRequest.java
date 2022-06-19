package com.fpsoluctionstechs.hortfruitonline.controller.produto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ProdutoIdRequest {
	@NotNull
	private Long id;

}
