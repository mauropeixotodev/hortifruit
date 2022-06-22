package com.fpsoluctionstechs.hortfruitonline.enums;

public enum StatusPedido {
	AGUARDANDO_CLIENTE_CONFIRMAR_PEDIDO("Aguardando a confirmação","Aguardando o cliente confirmar o pedido.", "faPhone"),
	AGUARDANDO_PROCESSAMENTO("Aguardando Processamento", "Aguardando processamento do pedido.", "faCirclePause"),
	PROCESSANDO("Processando", "Processando o pedido.", "faArrowsSpin"),
	CANCELADO("Cancelado", "Pedido cancelado.", "faCancel"),
	ENTREGUE("Entregue", "Pedido entrege.", "faCar");
	
	private String nome;
	private String descricao;
	private String iconName;

	StatusPedido(String nome, String descricao, String iconName){
		this.nome = nome;
		this.descricao = descricao;
		this.iconName = iconName;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getIconName() {
		return iconName;
	}
}
