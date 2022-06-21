package com.fpsoluctionstechs.hortfruitonline.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
public class Categoria {

	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria_seq")
	@SequenceGenerator(name = "categoria_seq", sequenceName = "categoria_seq", allocationSize = 1)
	@EqualsAndHashCode.Include
	private Long id;

	@ManyToMany(mappedBy = "categorias")
	private List<Produto> produtos;

	private String nome;
	
	private int orderExibicao;

}
