package com.example.hortfruitonline.model;

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
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco_seq")
	@SequenceGenerator(name = "endereco_seq", sequenceName = "endereco_seq", allocationSize = 1)
	private Long id;

	private String numero;
	private String logradouro;
	private String complemento;
	private String bairro;
	private String referencia;

	private double latitude;
	private double longitude;
}
