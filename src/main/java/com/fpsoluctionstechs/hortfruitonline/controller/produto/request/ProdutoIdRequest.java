package com.fpsoluctionstechs.hortfruitonline.controller.produto.request;



import org.springframework.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProdutoIdRequest {
	@NonNull
	private Long id;

}
