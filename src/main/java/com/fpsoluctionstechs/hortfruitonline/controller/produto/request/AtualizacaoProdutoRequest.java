package com.fpsoluctionstechs.hortfruitonline.controller.produto.request;

import java.util.List;


import com.fpsoluctionstechs.hortfruitonline.controller.categoria.request.CategoriaProdutoRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AtualizacaoProdutoRequest {
	@NotNull
	private Long id;
	
	private String imagem;
	
	private String nome;
	
	private String descricao;
	
	private List<CategoriaProdutoRequest> categorias;
	
	private List<MedidaProdutoRequest> medidas;
	

}
