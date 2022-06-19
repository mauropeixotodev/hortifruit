package com.fpsoluctionstechs.hortfruitonline.controller.produto.response;

import com.fpsoluctionstechs.hortfruitonline.controller.medida.request.MedidaIdRequest;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;

@Data
@Builder
public class MedidaProdutoResponse {
    private Long id;
    private String nome;
    private BigDecimal unidadeEmGramas;
    private BigDecimal preco;
}
