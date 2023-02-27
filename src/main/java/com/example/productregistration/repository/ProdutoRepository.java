package com.example.productregistration.repository;

import com.example.productregistration.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
* A interface ProdutoRepository herda da interface JpaRepository,
* que fornece as operações básicas de CRUD para a entidade Produto.*/

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByNomeContainingIgnoreCase(String nome);

}
