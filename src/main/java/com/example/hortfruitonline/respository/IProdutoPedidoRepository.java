package com.example.hortfruitonline.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hortfruitonline.model.ProdutoPedido;

public interface IProdutoPedidoRepository extends JpaRepository<ProdutoPedido, Long> {

}
