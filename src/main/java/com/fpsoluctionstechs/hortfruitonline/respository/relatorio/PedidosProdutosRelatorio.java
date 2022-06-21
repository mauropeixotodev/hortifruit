package com.fpsoluctionstechs.hortfruitonline.respository.relatorio;

import java.math.BigDecimal;

public interface PedidosProdutosRelatorio {

    String getProduto();
    BigDecimal getQuantidade();
    BigDecimal getPreco();
    String getMedida();
}
