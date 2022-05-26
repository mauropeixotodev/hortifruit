package com.hortfruitonline;

import com.hortfruitonline.model.*;
import com.hortfruitonline.respository.*;
import com.hortfruitonline.model.*;
import com.hortfruitonline.respository.CategoriaRepository;
import com.hortfruitonline.respository.PedidoRepository;
import com.hortfruitonline.respository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Arrays;

@SpringBootApplication
public class HortfruitOnlineApplication {

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	PedidoRepository pedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(HortfruitOnlineApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			Medida medidaKgArroz = Medida.builder()
					.preco(BigDecimal.valueOf(12l))
					.descricao("2,5 KG")
					.unidadeEmGramas(BigDecimal.valueOf(2500))
					.build();

			Categoria categoriaPromocao = Categoria.builder()
					.nome("Promoção")
					.build();

			Produto arroz = Produto.builder()
					.nome("Arroz")
					.descricao("Arroz branco")
					.medidas(Arrays.asList(medidaKgArroz))
					.categorias(Arrays.asList(categoriaPromocao))
					.build();

			//todo precisamos definir o produto da medida
			medidaKgArroz.setProduto(arroz);

			categoriaRepository.save(categoriaPromocao);

			produtoRepository.save(arroz);


			Pedido pedido = Pedido.builder()
					.cliente("Mauro")
					.contato("83981818282")
					.endereco(Endereco.builder()
							.referencia("Rua Nove")
							.bairro("Inhauma")
							.complemento("")
							.latitude(-22.860975092682903)
							.longitude(-43.26979270245136)
							.numero("2b")
							.referencia("Completo do Alemão")
							.build()
					)
					.build();

			pedido.setProdutoPedidos(
					Arrays.asList(
						ProdutoPedido.builder()
								.unidadeMedidaGrama(medidaKgArroz.getUnidadeEmGramas())
								.produto(Produto.builder().id(arroz.getId()).build())
								.quantidade(2)
								.precoUnidade(medidaKgArroz.getPreco())
								.precoTotal(medidaKgArroz.getPreco().multiply(BigDecimal.valueOf(2)))
								.totalMedidaGrama(medidaKgArroz.getUnidadeEmGramas().multiply(BigDecimal.valueOf(2)))
								.build())
			);

			pedido.setValorTotal(pedido.getProdutoPedidos().stream().map(produtoPedido -> produtoPedido.getPrecoTotal()).reduce(BigDecimal.valueOf(0), BigDecimal::add));

			pedidoRepository.save(pedido);
		};
	}

}
