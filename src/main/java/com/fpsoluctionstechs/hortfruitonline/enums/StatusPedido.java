package com.fpsoluctionstechs.hortfruitonline.enums;

public enum StatusPedido {
	AGUARDANDO_CLIENTE_CONFIRMAR_PEDIDO("Aguardando a confirmação","Aguardando o cliente confirmar o pedido."),
	AGUARDANDO_PROCESSAMENTO("Aguardando Processamento", "Aguardando processamento do pedido."),
	PROCESSANDO("Processando", "Processando o pedido."),
	CANCELADO("Cancelado", "Pedido cancelado."),
	ENTREGE("Entrege", "Pedido entrege.");
	
	private String nome;
	private String descricao;

	StatusPedido(String nome, String descricao){
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getNome() {
		return nome;
	}
}
