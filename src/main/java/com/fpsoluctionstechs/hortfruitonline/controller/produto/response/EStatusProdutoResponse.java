package com.fpsoluctionstechs.hortfruitonline.controller.produto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EStatusProdutoResponse {
    private String key;
    private String nome;
    private String descricao;
}
