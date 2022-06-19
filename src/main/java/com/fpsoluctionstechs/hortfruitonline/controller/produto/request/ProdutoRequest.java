package com.fpsoluctionstechs.hortfruitonline.controller.produto.request;

import java.util.List;
import com.fpsoluctionstechs.hortfruitonline.controller.categoria.request.CategoriaProdutoRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ProdutoRequest {
	@NotNull
	private String nome;
	@NotNull
	private String descricao;
	@NotNull
	private String imagem;
	@NotNull
	@NotEmpty
	private List<CategoriaProdutoRequest> categorias;
	@NotNull
	@NotEmpty
	private List<MedidaProdutoRequest> medidas;

}
