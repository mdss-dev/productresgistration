package com.example.productregistration.controller;

import com.example.productregistration.entity.Produto;
import com.example.productregistration.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;


    @GetMapping
    public String listar(Model model){
        model.addAllAttributes("produtos", produtoRepository.findAll());
        return "produtos/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model){
        model.addAllAttributes("produto", new Produto());
        return "produtos/formulario";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable("id") Long id, Model model){
            .orElseThrow(() -> new IllegalArgumentException("Produto inválido"));
        model.addAllAttributes("produto", produto);
    }

    @GetMapping("/salvar")
    public String salvar(@ModelAttribute("produto") Produto produto) {
        produtoRepository.save(produto);
        return "redirect:/produtos";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable("id") Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto inválido!"));
        produtoRepository.delete(produto);
        return "redirect:/produtos";
    }
}
