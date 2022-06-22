package com.fpsoluctionstechs.hortfruitonline.model;

import java.math.BigDecimal;
import javax.persistence.*;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
public class ProdutoPedido {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_pedido_seq")
	@SequenceGenerator(name = "produto_pedido_seq", sequenceName = "produto_pedido_seq", allocationSize = 1)
	@EqualsAndHashCode.Include
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "produto_id", referencedColumnName = "id", nullable = false)
	private Produto produto;

	@ManyToOne
	@JoinColumn(name = "pedido_id", referencedColumnName = "id", nullable = false)
	private Pedido pedido;
    @Column(nullable = false)
	private int quantidade;
	private BigDecimal unidadeMedidaGrama;
	private BigDecimal precoUnidade;

	@ManyToOne
	private MedidaProduto medidaProduto;
	
    @Column(nullable = false)
	private BigDecimal precoTotal;
    @Column(nullable = false)
	private BigDecimal totalMedidaGrama;
}
