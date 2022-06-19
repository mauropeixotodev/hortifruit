package com.fpsoluctionstechs.hortfruitonline.model;

import java.math.BigDecimal;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProdutoPedido {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_pedido_seq")
	@SequenceGenerator(name = "produto_pedido_seq", sequenceName = "produto_pedido_seq", allocationSize = 1)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "produto_id", referencedColumnName = "id", nullable = false)
	private Produto produto;

	@ManyToOne
	@JoinColumn(name = "pedido_id", referencedColumnName = "id", nullable = false)
	private Pedido pedido;
	
	private int quantidade;
	private BigDecimal unidadeMedidaGrama;
	private BigDecimal precoUnidade;

	@ManyToOne
	private MedidaProduto medidaProduto;

	private BigDecimal precoTotal;
	private BigDecimal totalMedidaGrama;
}
