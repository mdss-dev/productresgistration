package com.example.productregistration.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import javax.annotation.processing.Generated;

public class Produto {

    /*
    * A anotação @Entity indica que esta classe representa uma entidade JPA.
    *  Os campos id, nome, preco e quantidade representam os atributos da entidade.
    A anotação @Id indica que o campo id é a chave primária da entidade.
    * A anotação @GeneratedValue indica que o valor do campo id será gerado automaticamente
    * pelo banco de dados.*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Double preco;

    private Integer quantidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
