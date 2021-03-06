package com.fpsoluctionstechs.hortfruitonline.controller.medida.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class MedidaIdRequest {
	@NotNull
	@Min(value = 0)
	private Long id;
}
