package com.fpsoluctionstechs.hortfruitonline.controller.categoria.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CategoriaRequest {
	@NotNull
	private String nome;
	@NotNull
	private int orderExibicao;
}
