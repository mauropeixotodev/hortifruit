package com.fpsoluctionstechs.hortfruitonline.model;

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
public class Medida {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medida_seq")
	@SequenceGenerator(name = "medida_seq", sequenceName = "medida_seq", allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "produto_id", referencedColumnName = "id", nullable = false)
	private Produto produto;

	private String descricao;
	private BigDecimal unidadeEmGramas;
	private BigDecimal preco;
}
