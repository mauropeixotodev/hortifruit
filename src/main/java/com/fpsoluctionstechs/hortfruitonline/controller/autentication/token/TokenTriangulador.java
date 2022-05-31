package com.fpsoluctionstechs.hortfruitonline.controller.autentication.token;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenTriangulador {

	private String token;

	private Date dataDeExpiracao;
}
