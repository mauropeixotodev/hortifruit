package com.fpsoluctionstechs.hortfruitonline.controller.produto.request;

import com.fpsoluctionstechs.hortfruitonline.controller.medida.request.MedidaIdRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class MedidaProdutoRequest {
    @NotNull
    private MedidaIdRequest medida;
    @NotNull
    private BigDecimal preco;
}
