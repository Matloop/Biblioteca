package com.example.bibliotecasenai.controllers;

import com.example.bibliotecasenai.models.Livro;
import com.example.bibliotecasenai.models.Usuario;
import com.example.bibliotecasenai.services.LivroService;
import com.example.bibliotecasenai.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.bibliotecasenai.models.Emprestimo;
import com.example.bibliotecasenai.services.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {
    private final EmprestimoService emprestimoService;
    private final UsuarioService usuarioService;
    private final LivroService livroService;
    @Autowired
    public EmprestimoController(EmprestimoService emprestimoService, UsuarioService usuarioService, LivroService livroService) {
        this.emprestimoService = emprestimoService;
        this.usuarioService = usuarioService;
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<Emprestimo> postEmprestimo(@RequestBody Emprestimo emprestimo) {
        Emprestimo salvarEmprestimo = emprestimoService.fazerEmprestimo(emprestimo);
        return new ResponseEntity<>(salvarEmprestimo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Emprestimo>> getEmprestimos() {
        List<Emprestimo> emprestimos =  emprestimoService.listarEmprestimos();
        return new ResponseEntity<>(emprestimos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> getEmprestimo(@PathVariable Long id) {
        Optional<Emprestimo> emprestimo = emprestimoService.buscarEmprestimoPorId(id);
        return emprestimo.map(emprestimo1 -> new ResponseEntity<>(emprestimo1, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emprestimo> atualizarEmprestimo(@PathVariable Long id, @RequestBody Emprestimo emprestimo) {
        Emprestimo emprestimoExistente = emprestimoService.atualizarEmprestimo(emprestimo);
        return emprestimoExistente != null ? new ResponseEntity<>(emprestimoExistente, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/mostrar")
    public String mostrarEmprestimos(Model model) {
        // Obtém a lista de empréstimos do serviço
        List<Emprestimo> emprestimos = emprestimoService.listarEmprestimos();

        // Adiciona a lista ao modelo para ser usada no Thymeleaf
        model.addAttribute("emprestimos", emprestimos);

        // Retorna o template index.html
        return "emprestimo/listaEmprestimo";
    }

    @GetMapping("/salvar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("emprestimo", new Emprestimo());
        model.addAttribute("livros", livroService.findAll());
        model.addAttribute("usuarios", usuarioService.findAll());
        return "emprestimo/formularioEmprestimo";
    }

    @PostMapping("/salvar")
    public String salvarEmprestimo(
            @ModelAttribute("emprestimo") Emprestimo emprestimo,
            @RequestParam("livro.id") Long livroId,
            @RequestParam("usuario.id") Long usuarioId
    ) {
        // Busca o livro e usuário pelo ID
        Livro livro = livroService.findById(livroId)
                .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado"));
        Usuario usuario = usuarioService.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));;

        // Atribui ao empréstimo
        emprestimo.setLivro(livro);
        emprestimo.setUsuario(usuario);

        // Salva o empréstimo
        emprestimoService.fazerEmprestimo(emprestimo);

        return "redirect:/emprestimos/mostrar";
    }
}
