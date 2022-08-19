package com.fpsoluctionstechs.hortfruitonline.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
public class RegraPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "regra_pedido_seq")
    @SequenceGenerator(name = "regra_pedido_seq", sequenceName = "regra_pedido_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private Long id;

    private BigDecimal valorMinimo;
}
