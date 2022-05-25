package com.example.hortfruitonline.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hortfruitonline.model.Pedido;

public interface IPedidoRepository extends JpaRepository<Pedido, Long> {

}
