package com.fpsoluctionstechs.hortfruitonline.controller.medida.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class AtualizarMedidaRequest {
	@NotNull
	private Long id;
	@NotNull
	private String nome;
	@NotNull
	private BigDecimal unidadeEmGramas;
}
