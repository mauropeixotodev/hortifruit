package com.fpsoluctionstechs.hortfruitonline.service;

import com.fpsoluctionstechs.hortfruitonline.controller.categoria.request.AtualizarCategoriaRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.categoria.request.CategoriaRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.categoria.response.CategoriaResponse;
import com.fpsoluctionstechs.hortfruitonline.controller.categoria.response.CategoriaResponseGet;
import com.fpsoluctionstechs.hortfruitonline.controller.medida.request.AtualizarMedidaRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.medida.request.MedidaRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.medida.request.MedidaResponseGet;
import com.fpsoluctionstechs.hortfruitonline.controller.medida.response.MedidaResponse;
import com.fpsoluctionstechs.hortfruitonline.model.Categoria;
import com.fpsoluctionstechs.hortfruitonline.model.Medida;
import com.fpsoluctionstechs.hortfruitonline.respository.CategoriaRepository;
import com.fpsoluctionstechs.hortfruitonline.respository.MedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedidaService {

	@Autowired
	private MedidaRepository medidaRepository;

	public MedidaResponse salvar(MedidaRequest medidaRequest) {
		return builderResponse(medidaRepository.save(builder(medidaRequest)));
	}

	public List<MedidaResponseGet> listar() {
		return medidaRepository.findAll().stream().map(medida -> builderResponseGet(medida))
				.collect(Collectors.toList());
	}

	@Transactional
	public MedidaResponse atualizar(AtualizarMedidaRequest atualizarMedidaRequest) {
		Optional<Medida> optional = medidaRepository.findById(atualizarMedidaRequest.getId());
		if (optional.isPresent()) {
			Medida medida = optional.get();
			medida.setNome(atualizarMedidaRequest.getNome());
			medida.setUnidadeEmGramas(atualizarMedidaRequest.getUnidadeEmGramas());
			return builderResponse(medida);
		}
		throw new EntityNotFoundException("Medida não encontrada");

	}

	public void deletar(Long id) {
		Optional<Medida> optional = medidaRepository.findById(id);
		if (optional.isPresent()) {
			medidaRepository.delete(optional.get());
			return;
		}
		throw new EntityNotFoundException("Medida não encontrada");
	}

	private MedidaResponse builderResponse(Medida medida) {
		return MedidaResponse.builder().id(medida.getId()).nome(medida.getNome()).unidadeEmGramas(medida.getUnidadeEmGramas()).build();
	}
	private MedidaResponseGet builderResponseGet(Medida medida) {
		return MedidaResponseGet.builder().id(medida.getId()).nome(medida.getNome()).unidadeEmGramas(medida.getUnidadeEmGramas()).build();
	}

	private Medida builder(MedidaRequest medidaRequest) {
		return Medida.builder().nome(medidaRequest.getNome()).unidadeEmGramas(medidaRequest.getUnidadeEmGramas()).build();
	}
}
