package com.example.hortfruitonline.service;

import com.example.hortfruitonline.model.*;
import com.example.hortfruitonline.respository.MedidaRepository;
import com.example.hortfruitonline.respository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    private MedidaRepository medidaRepository;

    //todo recebe o form com todos os dados do pedido
    public Pedido salvar(){
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


        pedido.setProdutoPedidos(builderProdutoPedidos());

        pedido.setValorTotal(calcularValorTotalPedido(pedido));

       return pedidoRepository.save(pedido);
    }

    private BigDecimal calcularValorTotalPedido(Pedido pedido){
        return pedido.getProdutoPedidos().stream().map(produtoPedido -> produtoPedido.getPrecoTotal()).reduce(BigDecimal.valueOf(0), BigDecimal::add);
    }

    private List<ProdutoPedido> builderProdutoPedidos(){

        List<ProdutoPedido> produtoPedidos = Arrays.asList();

//        formPedido.getProdutoPedido().foreach(produtoPeditoForm-> {
//
//           Optional<Medida> medidaOptional = medidaRepository.findById(produtoPeditoForm.getMedida().getId());
//
//           if (!medidaOptional.isPresent())
//               throw new Exception("Medida não encontrada");
//
//           Medida medida = medidaOptional.get();
//
//            produtoPedidos.add(
//                    ProdutoPedido.builder()
//                    .unidadeMedidaGrama(medida.getUnidadeEmGramas())
//                    .produto(Produto.builder().id(produtoPeditoForm.getProduto().getId()).build())
//                    .quantidade(2)
//                    .precoUnidade(medida.getPreco())
//                    .precoTotal(medida.getPreco().multiply(BigDecimal.valueOf(2)))
//                    .totalMedidaGrama(medida.getUnidadeEmGramas().multiply(BigDecimal.valueOf(2)))
//                    .build()
//            );
//        });

       return produtoPedidos;
    }
}
