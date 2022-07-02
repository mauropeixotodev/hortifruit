package com.fpsoluctionstechs.hortfruitonline.controller.produto.response;

import com.fpsoluctionstechs.hortfruitonline.controller.categoria.response.CategoriaResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProdutoResponse {
   
	private Long id;
	private boolean ativo;
	private String imagem;
	private String nome;
	private String descricao;
	private List<CategoriaResponse> categorias;
	private List<MedidaProdutoResponse> medidas;
	private EStatusProdutoResponse status;
}
