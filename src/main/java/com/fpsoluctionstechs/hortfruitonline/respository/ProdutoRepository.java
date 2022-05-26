package com.fpsoluctionstechs.hortfruitonline.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpsoluctionstechs.hortfruitonline.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
