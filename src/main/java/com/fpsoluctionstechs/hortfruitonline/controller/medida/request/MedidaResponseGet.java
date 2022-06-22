package com.fpsoluctionstechs.hortfruitonline.controller.medida.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class MedidaResponseGet {

	private Long id;
	private String nome;
	private BigDecimal unidadeEmGramas;

}
