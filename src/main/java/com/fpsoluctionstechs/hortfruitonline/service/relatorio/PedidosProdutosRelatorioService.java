package com.fpsoluctionstechs.hortfruitonline.service.relatorio;

import com.fpsoluctionstechs.hortfruitonline.controller.pedido.response.ProdutosPedidosRelatorioResponse;
import com.fpsoluctionstechs.hortfruitonline.enums.StatusPedido;
import com.fpsoluctionstechs.hortfruitonline.respository.relatorio.PedidosProdutosRelatorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidosProdutosRelatorioService {

    @Autowired
    private PedidosProdutosRelatorioRepository pedidosProdutosRelatorioRepository;

    public List<ProdutosPedidosRelatorioResponse> filtrarProdutosPedidosByStatus(StatusPedido statusPedido) {
        return pedidosProdutosRelatorioRepository.filtrarProdutosPedidosByStatus(statusPedido).stream().map(
                pedidosProdutosRelatorio -> ProdutosPedidosRelatorioResponse.builder()
                        .produto(pedidosProdutosRelatorio.getProduto())
                        .quantidade(pedidosProdutosRelatorio.getQuantidade())
                        .preco(pedidosProdutosRelatorio.getPreco())
                        .medida(pedidosProdutosRelatorio.getMedida())
                        .build()
        ).collect(Collectors.toList());
    }
}
