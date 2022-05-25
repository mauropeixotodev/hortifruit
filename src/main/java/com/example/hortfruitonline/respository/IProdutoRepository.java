package com.example.hortfruitonline.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hortfruitonline.model.Produto;

public interface IProdutoRepository extends JpaRepository<Produto, Long> {

}
