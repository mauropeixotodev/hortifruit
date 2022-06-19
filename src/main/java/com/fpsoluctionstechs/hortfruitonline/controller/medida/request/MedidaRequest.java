package com.fpsoluctionstechs.hortfruitonline.controller.medida.request;

import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class MedidaRequest {
	@NotNull
	private String nome;
	@NotNull
	private BigDecimal unidadeEmGramas;

}
