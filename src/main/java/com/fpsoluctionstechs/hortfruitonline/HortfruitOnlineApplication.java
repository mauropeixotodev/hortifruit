package com.fpsoluctionstechs.hortfruitonline;

import com.fpsoluctionstechs.hortfruitonline.model.*;
import com.fpsoluctionstechs.hortfruitonline.respository.CategoriaRepository;
import com.fpsoluctionstechs.hortfruitonline.respository.MedidaRepository;
import com.fpsoluctionstechs.hortfruitonline.respository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fpsoluctionstechs.hortfruitonline.respository.UsuarioRepository;

import java.math.BigDecimal;
import java.util.Arrays;

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
            Categoria categoriaPromocao = Categoria.builder().nome("Frutas").orderExibicao(1).build();

            categoriaRepository.save(categoriaFruta);
            categoriaRepository.save(categoriaPromocao);

            Medida medidaKG = Medida.builder().nome("KG").unidadeEmGramas(BigDecimal.valueOf(1000l)).build();
            medidaRepository.save(medidaKG);


            Produto banana = Produto.builder()
                    .descricao("Banana prata madura")
                    .nome("Banana prata madura")
                    .imagem("https://images.educamaisbrasil.com.br/content/banco_de_imagens/guia-de-estudo/D/banana-da-prata.jpg")
                    .categorias(Arrays.asList(categoriaFruta, categoriaPromocao))
                    .build();

            banana.setMedidas(Arrays.asList(
                    MedidaProduto.builder()
                            .medida(medidaKG).preco(BigDecimal.valueOf(12.5)).produto(banana)
                            .build()));
            produtoRepository.save(banana);
        };


    }


}
