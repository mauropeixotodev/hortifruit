package com.fpsoluctionstechs.hortfruitonline.model;

import javax.persistence.*;

import lombok.*;

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

	private String numero;
	private String logradouro;
	private String complemento;
	private String bairro;
	private String referencia;

	private double latitude;
	private double longitude;
}
