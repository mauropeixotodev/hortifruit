package com.fpsoluctionstechs.hortfruitonline.controller.produto.response;


import lombok.Builder;
import lombok.Data;


import java.math.BigDecimal;

@Data
@Builder
public class MedidaProdutoResponse {
    private Long id;
    private Long referenciaId;
    private String nome;
    private BigDecimal unidadeEmGramas;
    private BigDecimal preco;
}
