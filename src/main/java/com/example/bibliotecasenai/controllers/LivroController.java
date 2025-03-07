package com.example.bibliotecasenai.controllers;

import com.example.bibliotecasenai.models.Emprestimo;
import com.example.bibliotecasenai.models.Livro;
import com.example.bibliotecasenai.services.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    @Autowired
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }


    @GetMapping("/mostrar")
    public String mostrarLivros(Model model) {
        List<Livro> livros = livroService.findAll();
        model.addAttribute("livros", livros);
        return "livro/listaLivro"; // Certifique-se de que este caminho está correto
    }


    @PostMapping("/salvar") // Deve corresponder ao "th:action" do formulário
    public String salvarLivro(
            @Valid @ModelAttribute("livro") Livro livro,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            return "livro/formularioLivro"; // Volta ao formulário em caso de erro
        }
        livroService.salvarLivro(livro);
        redirectAttributes.addFlashAttribute("mensagem", "Livro salvo!");
        return "redirect:/livros/mostrar";
    }


    @GetMapping("/salvar")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("livro", new Livro()); // Adiciona um novo objeto Livro ao modelo
        return "livro/formularioLivro"; // Retorna o template do formulário
    }


}
