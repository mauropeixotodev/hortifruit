package com.fpsoluctionstechs.hortfruitonline.controller.medida.request;

import com.fpsoluctionstechs.hortfruitonline.controller.produto.response.ProdutoResponse;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class MedidaResponseGet {

	private Long id;
	private String nome;
	private BigDecimal unidadeEmGramas;

}
