package com.fpsoluctionstechs.hortfruitonline.controller.medida.request;



import java.math.BigDecimal;

import org.springframework.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MedidaRequest {
	@NonNull
	private String descricao;
	@NonNull
	private BigDecimal unidadeEmGramas;
	@NonNull
	private BigDecimal preco;
	
	

}
