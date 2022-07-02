package com.fpsoluctionstechs.hortfruitonline.respository;

import com.fpsoluctionstechs.hortfruitonline.enums.EStatusProduto;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fpsoluctionstechs.hortfruitonline.model.Categoria;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

    @Query(value = "select c as categoria, p as produto from Categoria c left join c.produtos p where p.status = :eStatusProduto", nativeQuery = false)
    List<ICategoria> findByProdutosStatus(EStatusProduto eStatusProduto);
}
