package com.fpsoluctionstechs.hortfruitonline.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpsoluctionstechs.hortfruitonline.controller.categoria.request.CategoriaProdutoRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.categoria.response.CategoriaResponse;
import com.fpsoluctionstechs.hortfruitonline.controller.medida.request.MedidaRequest;
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
	ProdutoRepository produtoRepository;

	@Autowired
	CategoriaRepository categoriaRepository;

	public ProdutoResponse salvarProduto(ProdutoRequest produtoRequest) {
		return builderProdutoResponse(produtoRepository.save(builderProduto(produtoRequest)));

	}

	public List<ProdutoResponse> builderProdutoResponse(List<Produto> produtos) {
		return produtos.stream().map(produto -> builderProdutoResponse(produto)).collect(Collectors.toList());
	}

	public Produto builderProduto(ProdutoRequest produtoRequest) {

		Produto produto = Produto.builder().nome(produtoRequest.getNome()).descricao(produtoRequest.getDescricao())
				.categorias(builderCategoria(produtoRequest.getCategorias()))
				.Imagem(produtoRequest.getImagem()).build();
		produto.setMedidas(builderMedida(produtoRequest.getMedidas(), produto));
		return produto;
	}

	public ProdutoResponse builderProdutoResponse(Produto produto) {
		return ProdutoResponse.builder().id(produto.getId()).descricao(produto.getDescricao()).nome(produto.getNome())
				.categorias(builderCategoriaResponse(produto.getCategorias()))
				.imagem(produto.getImagem())
				.medidas(builderMedidasResponse(produto.getMedidas())).build();
	}

	public List<Categoria> builderCategoria(List<CategoriaProdutoRequest> categoriasRequest) {
		return categoriasRequest.stream().map(categoriaRequest -> builderCategoria(categoriaRequest))
				.collect(Collectors.toList());
	}

	public Categoria builderCategoria(CategoriaProdutoRequest categoriaRequest) {

		Optional<Categoria> opitional = categoriaRepository.findById(categoriaRequest.getId());

		if (opitional.isPresent()) {

			return opitional.get();
		}

		throw new EntityNotFoundException("Categoria não encontrada");
	}

	public Medida builderMedida(MedidaRequest medida, Produto produto) {
		return Medida.builder().descricao(medida.getDescricao()).preco(medida.getPreco())
				.unidadeEmGramas(medida.getUnidadeEmGramas()).produto(produto).build();
	}

	public List<Medida> builderMedida(List<MedidaRequest> medidas, Produto produto) {
		return medidas.stream().map(medida -> builderMedida(medida, produto)).collect(Collectors.toList());
	}

	public List<MedidaResponse> builderMedidasResponse(List<Medida> medidas) {
		return medidas.stream().map(medida -> builderMedidaResponse(medida)).collect(Collectors.toList());
	}

	public MedidaResponse builderMedidaResponse(Medida medida) {
		return MedidaResponse.builder().descricao(medida.getDescricao()).preco(medida.getPreco())
				.unidadeEmGramas(medida.getUnidadeEmGramas()).build();
	}

	public List<CategoriaResponse> builderCategoriaResponse(List<Categoria> Categorias) {
		return Categorias.stream().map(categoria -> builderCategoriaResponse(categoria)).collect(Collectors.toList());
	}

	public CategoriaResponse builderCategoriaResponse(Categoria categoria) {
		return CategoriaResponse.builder().id(categoria.getId()).nome(categoria.getNome()).build();

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
				produto.setMedidas(builderMedida(atualizacaoProdutoRequest.getMedidas(), produto));
			}
			if(atualizacaoProdutoRequest.getImagem() != null) {
				produto.setImagem(atualizacaoProdutoRequest.getImagem());
			}

			return builderProdutoResponse(produto);
		}

		throw new EntityNotFoundException("Produto não encontrado");

	}

}
