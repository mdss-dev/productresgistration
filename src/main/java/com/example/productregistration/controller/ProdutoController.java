package com.example.productregistration.controller;

import com.example.productregistration.model.Produto;
import com.example.productregistration.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/")
    public String home() {
        return "produtos/index";
    }
    @GetMapping("/listar")
    public String listarProdutos(Model model) {
        List<Produto> produtos = produtoService.listarTodos();
        model.addAttribute("produtos", produtos);
        return "produtos/todosOsProdutos";
    }

    @GetMapping("/novo")
    public String exibirFormularioNovoProduto(Produto produto) {
        return "produtos/formulario";
    }

    @PostMapping("/salvar")
    public String salvarProduto(@Valid Produto produto, BindingResult result) {
        if (result.hasErrors()) {
            return "produtos/formulario";
        }
        produtoService.salvar(produto);
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String exibirFormularioEdicaoProduto(@PathVariable("id") Long id, Model model) {
        Produto produto = produtoService.buscarPorId(id);
        model.addAttribute("produto", produto);
        return "produtos/formulario";
    }

//    @PostMapping("/excluir/{id}")
//    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
//        Optional<Produto> produto = Optional.ofNullable(produtoService.buscarPorId(id));
//        if (produto.isPresent()) {
//            produtoService.excluir(id);
//            redirectAttributes.addFlashAttribute("mensagem", "Produto excluído com sucesso!");
//        } else {
//            redirectAttributes.addFlashAttribute("erro", "Não foi possível excluir o produto. Produto não encontrado.");
//        }
//        return "redirect:/produtos/todosOsProdutos";
//    }

    @PostMapping("excluir/{id}")
    @CacheEvict(value = "produto", allEntries = true)
    public String excluir(@PathVariable(name = "id") Long id, Model model) {
        Produto produto = produtoService.buscarPorId(id);
        produtoService.excluir(id);
        return "redirect:/";
    }

    @GetMapping("/buscarProduto")
    public String buscarPorId(Model model) {
        model.addAttribute("produto", new Produto());
        return "produtos/buscarProduto";
    }

    @GetMapping("/buscar")
    public String buscarProduto(@RequestParam("id") Long id, Model model) {
        Optional<Produto> produto = Optional.ofNullable(produtoService.buscarPorId(id));
        if (produto.isPresent()) {
            model.addAttribute("produto", produto.get());
            return "produtos/detalhes";
        }
        model.addAttribute("mensagem", "Produto não encontrado!");
        return "buscarProduto";
    }

    @GetMapping("/buscarPorNome")
    public ModelAndView buscarPorNome(@RequestParam String nome) {
        ModelAndView mv = new ModelAndView("produtos/buscarPorNome");
        List<Produto> produtos = produtoService.buscarPorNome(nome);
        if (produtos.isEmpty()) {
            mv.addObject("mensagem", "Nenhum produto encontrado com o nome: " + nome);
        } else {
            mv.addObject("produtos", produtos);
        }
        return mv;
    }
}