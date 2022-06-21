package com.fpsoluctionstechs.hortfruitonline.respository.relatorio;

import com.fpsoluctionstechs.hortfruitonline.enums.StatusPedido;
import com.fpsoluctionstechs.hortfruitonline.model.ProdutoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidosProdutosRelatorioRepository extends JpaRepository<ProdutoPedido, Long> {

    @Query(value =
            "SELECT pp.produto.nome AS produto, SUM(pp.quantidade) AS quantidade " +
                    ", SUM(pp.precoTotal) AS preco, pp.medidaProduto.medida.nome AS medida " +
                    " FROM ProdutoPedido pp WHERE pp.pedido.status = :statusPedido GROUP BY pp.produto")
    List<PedidosProdutosRelatorio> filtrarProdutosPedidosByStatus(StatusPedido statusPedido);
}
