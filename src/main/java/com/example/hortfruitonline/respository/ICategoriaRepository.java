package com.example.hortfruitonline.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hortfruitonline.model.Categoria;

public interface ICategoriaRepository  extends JpaRepository<Categoria, Long>{

}
