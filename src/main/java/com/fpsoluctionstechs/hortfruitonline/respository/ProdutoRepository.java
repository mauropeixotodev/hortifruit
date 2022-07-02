package com.fpsoluctionstechs.hortfruitonline.respository;

import com.fpsoluctionstechs.hortfruitonline.enums.EStatusProduto;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fpsoluctionstechs.hortfruitonline.model.Produto;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    public List<Produto> findByStatus(EStatusProduto status);
}
