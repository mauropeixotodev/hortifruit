package com.fpsoluctionstechs.hortfruitonline.model;

import javax.persistence.*;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco_seq")
	@SequenceGenerator(name = "endereco_seq", sequenceName = "endereco_seq", allocationSize = 1)
	@EqualsAndHashCode.Include
	private Long id;
    @Column(nullable = false)
	private String numero;
    @Column(nullable = false)
	private String logradouro;
    @Column(nullable = false)
	private String complemento;
    @Column(nullable = false)
	private String bairro;
    @Column(nullable = false)
	private String referencia;

	private BigDecimal latitude;
	private BigDecimal longitude;
}
