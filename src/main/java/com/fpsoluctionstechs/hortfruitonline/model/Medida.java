package com.fpsoluctionstechs.hortfruitonline.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
public class Medida {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medida_seq")
	@SequenceGenerator(name = "medida_seq", sequenceName = "medida_seq", allocationSize = 1)
	@EqualsAndHashCode.Include
	private Long id;
	
    @Column(nullable = false)
	private String nome;
    @Column(nullable = false)
	private BigDecimal unidadeEmGramas;
}
