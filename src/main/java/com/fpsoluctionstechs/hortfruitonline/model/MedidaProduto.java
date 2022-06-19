package com.fpsoluctionstechs.hortfruitonline.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MedidaProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medida_produto_seq")
	@SequenceGenerator(name = "medida_produto_seq", sequenceName = "medida_produto_seq", allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "produto_id", referencedColumnName = "id", nullable = false)
	private Produto produto;

	@ManyToOne
	@JoinColumn(name = "medida_id", referencedColumnName = "id")
	private Medida medida;

	@Column(nullable = false)
	private BigDecimal preco;
}
