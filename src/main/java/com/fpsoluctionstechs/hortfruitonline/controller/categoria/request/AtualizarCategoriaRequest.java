package com.fpsoluctionstechs.hortfruitonline.controller.categoria.request;

import org.springframework.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AtualizarCategoriaRequest {
	@NonNull
	private Long id;
	@NonNull
	private String nome;

}
