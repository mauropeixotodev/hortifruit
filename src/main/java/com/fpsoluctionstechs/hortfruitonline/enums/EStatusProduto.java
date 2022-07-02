package com.fpsoluctionstechs.hortfruitonline.enums;

public enum EStatusProduto {
    DISPONIVEL("Disponível", "Produto está disponível para venda"),
    INDISPONIVEL("Indisponível", "Produto não está disponível para venda.");

    private String nome;
    private String descricao;

    EStatusProduto(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

}
