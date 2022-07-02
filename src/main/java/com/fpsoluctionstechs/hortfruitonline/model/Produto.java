package com.fpsoluctionstechs.hortfruitonline.model;


import java.util.Set;

import javax.persistence.*;

import com.fpsoluctionstechs.hortfruitonline.enums.EStatusProduto;
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
    @Column(nullable = false)	
	private String imagem;

	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.EAGER)
	private Set<MedidaProduto> medidas;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "produto_categoria", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	private Set<Categoria> categorias;

    @Column(nullable = false)
	private String descricao;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EStatusProduto status;

	public void setMedidas(Set<MedidaProduto> medidas) {
		if (this.medidas != null && this.medidas.size() > 0) {
			this.medidas.forEach(produtoMedida -> produtoMedida.setProduto(null));
			this.medidas.clear();
			this.medidas.addAll(medidas);
			return;
		}

		this.medidas = medidas;
	}

}
