package com.fpsoluctionstechs.hortfruitonline;

import com.fpsoluctionstechs.hortfruitonline.controller.endereco.request.EnderecoRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.medida.request.MedidaIdRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.pedido.request.PedidoRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.produto.request.ProdutoIdRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.produto.request.ProdutoRequest;
import com.fpsoluctionstechs.hortfruitonline.controller.produtoPedido.request.ProdutoPedidoRequest;
import com.fpsoluctionstechs.hortfruitonline.enums.StatusPedido;
import com.fpsoluctionstechs.hortfruitonline.model.*;
import com.fpsoluctionstechs.hortfruitonline.respository.*;
import com.fpsoluctionstechs.hortfruitonline.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Collectors;

@SpringBootApplication
public class HortfruitOnlineApplication {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MedidaRepository medidaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoService pedidoService;

    public static void main(String[] args) {
        SpringApplication.run(HortfruitOnlineApplication.class, args);

    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {


            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            Usuario usuario = Usuario.builder()
                    .username("mauro")
                    .password(bCryptPasswordEncoder.encode("mauro"))
                    .build();
            usuarioRepository.save(usuario);


            Categoria categoriaFruta = Categoria.builder().nome("Frutas").orderExibicao(1).build();
            Categoria categoriaPromocao = Categoria.builder().nome("Promoção").orderExibicao(1).build();

            categoriaRepository.save(categoriaFruta);
            categoriaRepository.save(categoriaPromocao);

            Medida medidaKG = Medida.builder().nome("KG").unidadeEmGramas(BigDecimal.valueOf(1000l)).build();
            medidaRepository.save(medidaKG);


            Produto banana = Produto.builder()
                    .descricao("Banana prata madura")
                    .nome("Banana prata madura")
                    .imagem("https://images.educamaisbrasil.com.br/content/banco_de_imagens/guia-de-estudo/D/banana-da-prata.jpg")
                    .categorias(Arrays.asList(categoriaFruta, categoriaPromocao).stream().collect(Collectors.toSet()))
                    .build();

            banana.setMedidas(
                    Arrays.asList(
                            MedidaProduto.builder()
                                    .medida(medidaKG).preco(BigDecimal.valueOf(12.5)).produto(banana)
                            .build()
                    ).stream().collect(Collectors.toSet())
            );
            produtoRepository.save(banana);


            registrarPedido(banana, "Mauro", 20);
            registrarPedido(banana, "Damiao", 30);
            registrarPedido(banana, "Wenderson", 50);


            salvarPedidoByService();
        };


    }

    private void registrarPedido(Produto produto, String nomeCliente, int quantidade) {

        MedidaProduto medidaProduto = produto.getMedidas().stream().findFirst().get();

        ProdutoPedido produtoPedido = ProdutoPedido.builder()
                .produto(produto)
                .quantidade(quantidade)
                .medidaProduto(medidaProduto)
                .unidadeMedidaGrama(medidaProduto.getMedida().getUnidadeEmGramas())
                .totalMedidaGrama(medidaProduto.getMedida().getUnidadeEmGramas().multiply(BigDecimal.valueOf(quantidade)))
                .precoUnidade(medidaProduto.getPreco())
                .precoTotal(medidaProduto.getPreco().multiply(BigDecimal.valueOf(quantidade)))
                .build();

        Pedido pedido = Pedido.builder()
                .contato("83 986757463")
                .cliente(nomeCliente)
                .endereco(Endereco.builder()
                        .logradouro("Rua não conheço")
                        .numero("S/N")
                        .bairro("AP. 202")
                        .bairro("Imaculada")
                        .referencia("Escola senhor do bonfim")
                        .build())
                .produtoPedidos(Arrays.asList(produtoPedido))
                .build();

        produtoPedido.setPedido(pedido);
        pedido.setStatus(StatusPedido.AGURADANDO_PROCESSAMENTO);
        pedido.setValorTotal(medidaProduto.getPreco().multiply(BigDecimal.valueOf(quantidade)));
        pedidoRepository.save(pedido);
    }


    private void salvarPedidoByService() {
        PedidoRequest pedidoRequest = new PedidoRequest();
        pedidoRequest.setCliente("João Ferreira");
        pedidoRequest.setContato("21 9988312");

        EnderecoRequest enderecoRequest = new EnderecoRequest();
        enderecoRequest.setLogradouro("Rua chico duarte");
        enderecoRequest.setNumero("S/N");
        enderecoRequest.setComplemento("AP. 202");
        enderecoRequest.setBairro("Imaculada");
        enderecoRequest.setReferencia("Senhor do bonfim");
        pedidoRequest.setEndereco(enderecoRequest);


        ProdutoPedidoRequest produtoPedidoRequest = new ProdutoPedidoRequest();
        produtoPedidoRequest.setQuantidade(90);

        MedidaIdRequest medidaIdRequest = new MedidaIdRequest();
        medidaIdRequest.setId(1l);
        produtoPedidoRequest.setMedida(medidaIdRequest);

        ProdutoIdRequest produtoIdRequest = new ProdutoIdRequest();
        produtoIdRequest.setId(1l);
        produtoPedidoRequest.setProduto(produtoIdRequest);

        pedidoRequest.setProdutoPedidos(Arrays.asList(produtoPedidoRequest));

        pedidoService.salvarPedido(pedidoRequest);
    }


}
