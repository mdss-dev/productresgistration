package com.example.gerenciadoProdutos.service;

import com.example.gerenciadoProdutos.model.Produto;
import com.example.gerenciadoProdutos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {
        Optional<Produto> optionalProduto = produtoRepository.findById(id);
        if (optionalProduto.isPresent()) {
            return optionalProduto.get();
        }
        throw new RuntimeException("Produto não encontrado");
    }

    public List<Produto> buscarPorNome(String nome) {
        List<Produto> produtosEncontrados = produtoRepository.findByNomeContainingIgnoreCase(nome);
        if(produtosEncontrados.isEmpty()) {
            Collections.emptyList();
        }
        return produtosEncontrados;
    }

    public void salvar(Produto produto) {
        produtoRepository.save(produto);
    }

    public void excluir(Long id) {
        produtoRepository.deleteById(id);
    }
}
