package com.example.hortfruitonline.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hortfruitonline.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
