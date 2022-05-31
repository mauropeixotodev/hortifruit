package com.fpsoluctionstechs.hortfruitonline.controller.autentication.request;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioRequest {
	@NonNull
	private String username;
	@NonNull
	private String password;

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(this.username, this.password);
	}

}
