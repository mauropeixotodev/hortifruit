package com.fpsoluctionstechs.hortfruitonline.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.fpsoluctionstechs.hortfruitonline.controller.produto.request.MedidaProdutoRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.produto.response.MedidaProdutoResponse;
import com.fpsoluctionstechs.hortfruitonline.model.MedidaProduto;
import com.fpsoluctionstechs.hortfruitonline.respository.MedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpsoluctionstechs.hortfruitonline.controller.categoria.request.CategoriaProdutoRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.categoria.response.CategoriaResponse;
import com.fpsoluctionstechs.hortfruitonline.controller.medida.response.MedidaResponse;
import com.fpsoluctionstechs.hortfruitonline.controller.produto.request.AtualizacaoProdutoRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.produto.request.ProdutoRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.produto.response.ProdutoResponse;
import com.fpsoluctionstechs.hortfruitonline.model.Categoria;
import com.fpsoluctionstechs.hortfruitonline.model.Medida;
import com.fpsoluctionstechs.hortfruitonline.model.Produto;
import com.fpsoluctionstechs.hortfruitonline.respository.CategoriaRepository;
import com.fpsoluctionstechs.hortfruitonline.respository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private MedidaRepository medidaRepository;


	private Produto builderProduto(ProdutoRequest produtoRequest) {

		Produto produto = Produto.builder().nome(produtoRequest.getNome()).descricao(produtoRequest.getDescricao())
				.categorias(builderCategoria(produtoRequest.getCategorias()))
				.imagem(produtoRequest.getImagem()).build();
		produto.setMedidas(builderMedidaProduto(produtoRequest.getMedidas(), produto));
		return produto;
	}

	private Set<Categoria> builderCategoria(List<CategoriaProdutoRequest> categoriasRequest) {
		return categoriasRequest.stream().map(categoriaRequest -> builderCategoria(categoriaRequest))
				.collect(Collectors.toSet());
	}

	private Categoria builderCategoria(CategoriaProdutoRequest categoriaRequest) {

		Optional<Categoria> opitional = categoriaRepository.findById(categoriaRequest.getId());

		if (opitional.isPresent()) {

			return opitional.get();
		}

		throw new EntityNotFoundException("Categoria não encontrada");
	}


	private MedidaProduto builderMedidaProduto(MedidaProdutoRequest medida, Produto produto) {
		return MedidaProduto.builder().preco(medida.getPreco()).medida(buscarMedida(medida)).produto(produto).build();
	}

	private Medida buscarMedida(MedidaProdutoRequest medidaProdutoRequest) {

		Optional<Medida> optionalMedida = medidaRepository.findById(medidaProdutoRequest.getMedida().getId());

		if (optionalMedida.isPresent()) {

			return optionalMedida.get();
		}

		throw new EntityNotFoundException("Medida não encontrada");
	}

	private Set<MedidaProduto> builderMedidaProduto(List<MedidaProdutoRequest> medidas, Produto produto) {
		return medidas.stream().map(medida -> builderMedidaProduto(medida, produto)).collect(Collectors.toSet());
	}

	private List<MedidaProdutoResponse> builderMedidasResponse(Set<MedidaProduto> medidas) {
		return medidas.stream().map(medida -> builderMedidaResponse(medida)).collect(Collectors.toList());
	}

	private MedidaProdutoResponse builderMedidaResponse(MedidaProduto medidaProduto) {
		return MedidaProdutoResponse.builder().id(medidaProduto.getId()).nome(medidaProduto.getMedida().getNome())
				.unidadeEmGramas(medidaProduto.getMedida().getUnidadeEmGramas()).preco(medidaProduto.getPreco()).build();
	}

	private List<CategoriaResponse> builderCategoriaResponse(Set<Categoria> Categorias) {
		return Categorias.stream().map(categoria -> builderCategoriaResponse(categoria)).collect(Collectors.toList());
	}

	private CategoriaResponse builderCategoriaResponse(Categoria categoria) {
		return CategoriaResponse.builder().id(categoria.getId()).nome(categoria.getNome()).build();

	}

	public ProdutoResponse salvarProduto(ProdutoRequest produtoRequest) {
		return builderProdutoResponse(produtoRepository.save(builderProduto(produtoRequest)));

	}

	public List<ProdutoResponse> builderProdutoResponse(List<Produto> produtos) {
		return produtos.stream().map(produto -> builderProdutoResponse(produto)).collect(Collectors.toList());
	}

	public ProdutoResponse builderProdutoResponse(Produto produto) {
		return ProdutoResponse.builder().id(produto.getId()).descricao(produto.getDescricao()).nome(produto.getNome())
				.categorias(builderCategoriaResponse(produto.getCategorias()))
				.imagem(produto.getImagem())
				.medidas(builderMedidasResponse(produto.getMedidas())).build();
	}


	public List<ProdutoResponse> listarProdutos() {
		return builderProdutoResponse(produtoRepository.findAll());
	}

	@Transactional
	public ProdutoResponse Atualizar(AtualizacaoProdutoRequest atualizacaoProdutoRequest) {

		Optional<Produto> optional = produtoRepository.findById(atualizacaoProdutoRequest.getId());
		if (optional.isPresent()) {
			Produto produto = optional.get();
			if (atualizacaoProdutoRequest.getNome() != null) {
				produto.setNome(atualizacaoProdutoRequest.getNome());
			}
			if (atualizacaoProdutoRequest.getDescricao() != null) {
				produto.setDescricao(atualizacaoProdutoRequest.getDescricao());

			}
			if (atualizacaoProdutoRequest.getCategorias().size() != 0) {
				produto.setCategorias(builderCategoria(atualizacaoProdutoRequest.getCategorias()));

			}
			if (atualizacaoProdutoRequest.getMedidas().size() != 0) {
				produto.setMedidas(builderMedidaProduto(atualizacaoProdutoRequest.getMedidas(), produto));
			}
			if(atualizacaoProdutoRequest.getImagem() != null) {
				produto.setImagem(atualizacaoProdutoRequest.getImagem());
			}

			return builderProdutoResponse(produto);
		}

		throw new EntityNotFoundException("Produto não encontrado");

	}

}
