package com.fpsoluctionstechs.hortfruitonline.controller.endereco.request;

import org.springframework.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EnderecoRequest {

	@NonNull
	private String numero;

	@NonNull
	private String logradouro;

	@NonNull
	private String complemento;

	@NonNull
	private String bairro;

	@NonNull
	private String referencia;

	@NonNull
	private double latitude;

	@NonNull
	private double longitude;

}
