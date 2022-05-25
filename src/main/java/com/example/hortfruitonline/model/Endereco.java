package com.example.hortfruitonline.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Entity
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco_seq")
	@SequenceGenerator(name = "endereco_seq", sequenceName = "endereco_seq", allocationSize = 1)
	private Long id;
	private String bairro;
	private int numero;
	private String complemento;
	private String referencia;
}
