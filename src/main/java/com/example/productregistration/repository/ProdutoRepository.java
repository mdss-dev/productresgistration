package com.example.productregistration.repository;

import com.example.productregistration.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

/*
* A interface ProdutoRepository herda da interface JpaRepository,
* que fornece as operações básicas de CRUD para a entidade Produto.*/

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
