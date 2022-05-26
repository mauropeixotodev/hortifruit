package com.example.hortfruitonline.model;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pp_seq")
	@SequenceGenerator(name = "pp_seq", sequenceName = "pp_seq", allocationSize = 1)
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

	private BigDecimal precoTotal;
	private BigDecimal totalMedidaGrama;
}
