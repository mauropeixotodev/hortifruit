package com.fpsoluctionstechs.hortfruitonline.controller.produto.request;

import java.util.List;

import org.springframework.lang.NonNull;

import com.fpsoluctionstechs.hortfruitonline.controller.categoria.request.CategoriaProdutoRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.medida.request.MedidaRequest;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AtualizacaoProdutoRequest {
	@NonNull
	private Long id;
	
	private String imagem;
	
	private String nome;
	
	private String descricao;
	
	private List<CategoriaProdutoRequest> categorias;
	
	private List<MedidaRequest> medidas;
	

}
