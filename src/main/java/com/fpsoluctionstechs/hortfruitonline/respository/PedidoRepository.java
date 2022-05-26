package com.fpsoluctionstechs.hortfruitonline.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpsoluctionstechs.hortfruitonline.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
