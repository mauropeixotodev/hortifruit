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
public class MedidaProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medida_produto_seq")
	@SequenceGenerator(name = "medida_produto_seq", sequenceName = "medida_produto_seq", allocationSize = 1)
	@EqualsAndHashCode.Include
	private Long id;

	@ManyToOne
	@JoinColumn(name = "produto_id", referencedColumnName = "id")
	private Produto produto;

	@ManyToOne
	@JoinColumn(name = "medida_id", referencedColumnName = "id")
	private Medida medida;

	@Column(nullable = false)
	private BigDecimal preco;
}
