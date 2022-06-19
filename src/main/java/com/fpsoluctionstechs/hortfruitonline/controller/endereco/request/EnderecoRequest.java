package com.fpsoluctionstechs.hortfruitonline.controller.endereco.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class EnderecoRequest {

	@NotNull
	private String numero;

	@NotNull
	private String logradouro;

	@NotNull
	private String complemento;

	@NotNull
	private String bairro;

	@NotNull
	private String referencia;

	@NotNull
	private double latitude;

	@NotNull
	private double longitude;

}
