package com.fpsoluctionstechs.hortfruitonline.model;

import java.util.List;

import javax.persistence.*;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_seq")
	@SequenceGenerator(name = "produto_seq", sequenceName = "produto_seq", allocationSize = 1)
	@EqualsAndHashCode.Include //todo anotar as demais classe também
	private Long id;

	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Medida> medidas;

	@ManyToMany
	@JoinTable(name = "produto_categoria", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	private List<Categoria> categorias;

	private String descricao;
	private String nome;

	//todo remover apos criação do produtoService
//	public Produto(Long id, List<Medida> medidas, List<Categoria> categorias, String descricao, String nome) {
//		this.id = id;
//		this.categorias = categorias;
//		this.descricao = descricao;
//		this.nome = nome;
//		setMedidas(medidas);
//	}
//
	public void setMedidas(List<Medida> medidas) {
		if(this.medidas != null) {
			this.medidas.clear();
			this.medidas.addAll(medidas);
			return;
		}
		
		this.medidas = medidas;
	}

}
