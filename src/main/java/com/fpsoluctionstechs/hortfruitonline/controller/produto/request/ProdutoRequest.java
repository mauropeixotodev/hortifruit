package com.fpsoluctionstechs.hortfruitonline.controller.produto.request;

import java.util.List;

import org.springframework.lang.NonNull;

import com.fpsoluctionstechs.hortfruitonline.controller.categoria.request.CategoriaProdutoRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.medida.request.MedidaRequest;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProdutoRequest {
	@NonNull
	private String nome;
	@NonNull
	private String descricao;
	@NonNull
	private String imagem;
	@NonNull
	private List<CategoriaProdutoRequest> categorias;
	@NonNull
	private List<MedidaRequest> medidas;

}
