package com.fpsoluctionstechs.hortfruitonline.enums;

public enum StatusPedido {
	AGURADANDO_PROCESSAMENTO("Aguardando Processamento"), PROCESSANDO("Processando"), CANCELADO("Cancelado"), ENTREGE("Entrege");
	
	private String descricao;
	
	StatusPedido(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
