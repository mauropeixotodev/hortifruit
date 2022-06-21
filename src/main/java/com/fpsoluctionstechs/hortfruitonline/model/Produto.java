package com.fpsoluctionstechs.hortfruitonline.model;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_seq")
	@SequenceGenerator(name = "produto_seq", sequenceName = "produto_seq", allocationSize = 1)
	@EqualsAndHashCode.Include
	private Long id;
	
	private String imagem;

	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<MedidaProduto> medidas;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "produto_categoria", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	private Set<Categoria> categorias;

	private String descricao;
	private String nome;

//	public void setMedidas(Set<MedidaProduto> medidas) {
//		if (this.medidas != null) {
//			this.medidas.clear();
//			this.medidas.addAll(medidas);
//			return;
//		}
//
//		this.medidas = medidas;
//	}

}
