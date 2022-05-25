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

@Data
@Builder
@AllArgsConstructor
@Entity
public class Medida {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medida_seq")
	@SequenceGenerator(name = "medida_seq", sequenceName = "medida_seq", allocationSize = 1)
	private Long id;

	private String tipo;

	private BigDecimal valor;

	private BigDecimal preco;

	@ManyToOne
	@JoinColumn(name = "produto_id", referencedColumnName = "id", nullable = false)
	private Produto produto;
}
