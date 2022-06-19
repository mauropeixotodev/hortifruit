package com.fpsoluctionstechs.hortfruitonline.controller.medida.response;


import java.math.BigDecimal;



import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedidaResponse {

	private Long id;
	private String nome;
	private BigDecimal unidadeEmGramas;
}
