package com.fpsoluctionstechs.hortfruitonline.controller.categoria.response;

import java.util.List;

import com.fpsoluctionstechs.hortfruitonline.controller.produto.response.ProdutoResponse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoriaResponseGet {

	private Long id;
	private String nome;
	private List<ProdutoResponse> produtos;

}
