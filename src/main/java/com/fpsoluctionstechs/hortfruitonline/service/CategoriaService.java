package com.fpsoluctionstechs.hortfruitonline.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.fpsoluctionstechs.hortfruitonline.controller.categoria.request.AtualizarCategoriaRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.categoria.request.CategoriaRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.categoria.response.CategoriaResponse;
import com.fpsoluctionstechs.hortfruitonline.controller.categoria.response.CategoriaResponseGet;
import com.fpsoluctionstechs.hortfruitonline.model.Categoria;
import com.fpsoluctionstechs.hortfruitonline.respository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	ProdutoService produtoService;

	@Autowired
	CategoriaRepository categoriaRepository;

	public CategoriaResponse salvar(CategoriaRequest categoriaRequest) {
		return builderCategoriaResponse(categoriaRepository.save(builderCategoria(categoriaRequest)));
	}

	public List<CategoriaResponseGet> buscarCategorias() {
		return categoriaRepository.findAll().stream().map(categoria -> builderCategoriaResponseGet(categoria))
				.collect(Collectors.toList());
	}

	@Transactional
	public CategoriaResponse atualizarCategoria(AtualizarCategoriaRequest atualizarCategoriaRequest) {
		Optional<Categoria> optional = categoriaRepository.findById(atualizarCategoriaRequest.getId());
		if (optional.isPresent()) {
			Categoria categoria = optional.get();
			if(atualizarCategoriaRequest.getNome() != null) {
				categoria.setNome(atualizarCategoriaRequest.getNome());
			}
			if(atualizarCategoriaRequest.getOrderExibicao() != 0) {
				categoria.setOrderExibicao(atualizarCategoriaRequest.getOrderExibicao());
			}
			
			return builderCategoriaResponse(categoria);
		}
		throw new EntityNotFoundException("Categoria não encontrada");

	}

	public void deletarCategoria(Long id) {
		Optional<Categoria> optional = categoriaRepository.findById(id);
		if (optional.isPresent()) {
			categoriaRepository.delete(optional.get());
			return;
		}
		throw new EntityNotFoundException("Categoria não encontrada");
	}

	private CategoriaResponse builderCategoriaResponse(Categoria categoria) {
		return CategoriaResponse.builder().id(categoria.getId()).nome(categoria.getNome())
				.orderExibicao(categoria.getOrderExibicao()).build();
	}

	private CategoriaResponseGet builderCategoriaResponseGet(Categoria categoria) {
		return CategoriaResponseGet.builder().id(categoria.getId()).nome(categoria.getNome())
				.produtos(produtoService.builderProdutoResponse(categoria.getProdutos()))
				.orderExibicao(categoria.getOrderExibicao()).build();
	}

	private Categoria builderCategoria(CategoriaRequest categoriaRequest) {
		return Categoria.builder().nome(categoriaRequest.getNome()).orderExibicao(categoriaRequest.getOrderExibicao())
				.build();
	}

}
