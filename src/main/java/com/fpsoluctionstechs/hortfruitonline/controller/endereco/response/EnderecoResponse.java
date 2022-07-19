package com.fpsoluctionstechs.hortfruitonline.controller.endereco.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class EnderecoResponse {

	private Long id;
	private String numero;
	private String logradouro;
	private String complemento;
	private String bairro;
	private String referencia;
	private BigDecimal latitude;
	private BigDecimal longitude;

}
