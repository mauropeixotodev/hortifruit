package com.fpsoluctionstechs.hortfruitonline.controller.produto.response;


import java.util.List;

import com.fpsoluctionstechs.hortfruitonline.controller.categoria.response.CategoriaResponse;
import com.fpsoluctionstechs.hortfruitonline.controller.medida.response.MedidaResponse;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class ProdutoResponse {
   
	private Long id;
	
	private String imagem;
	
	private String nome;

	private String descricao;

	private List<CategoriaResponse> categorias;

	private List<MedidaResponse> medidas;

}
