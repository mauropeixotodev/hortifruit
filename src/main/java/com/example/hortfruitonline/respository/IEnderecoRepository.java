package com.example.hortfruitonline.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hortfruitonline.model.Endereco;

public interface IEnderecoRepository extends JpaRepository<Endereco, Long> {

}
