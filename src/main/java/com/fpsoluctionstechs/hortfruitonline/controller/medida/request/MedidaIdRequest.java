package com.fpsoluctionstechs.hortfruitonline.controller.medida.request;

import org.springframework.lang.NonNull;



import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class MedidaIdRequest {
	@NonNull
	private Long id;
}
