package com.example.bibliotecasenai.controllers;

import com.example.bibliotecasenai.models.Emprestimo;
import com.example.bibliotecasenai.models.Livro;
import com.example.bibliotecasenai.models.Usuario;
import com.example.bibliotecasenai.services.UsuarioService;
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
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;
    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/mostrar")
    public String mostrarUsuarios(Model model) {
        // Obtém a lista de empréstimos do serviço
        List<Usuario> usuarios = usuarioService.findAll();

        // Adiciona a lista ao modelo para ser usada no Thymeleaf
        model.addAttribute("usuarios", usuarios);

        // Retorna o template index.html
        return "usuario/listaUsuario";
    }

    @PostMapping("/salvar") // Deve corresponder ao "th:action" do formulário
    public String salvarUsuario(
            @Valid @ModelAttribute("usuario") Usuario usuario,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            return "livro/formularioUsuario"; // Volta ao formulário em caso de erro
        }
        usuarioService.salvarUsuario(usuario);
        redirectAttributes.addFlashAttribute("mensagem", "Usuario salvo!");
        return "redirect:/usuarios/mostrar";
    }


    @GetMapping("/salvar")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("usuario", new Usuario()); // Adiciona um novo objeto Livro ao modelo
        return "usuario/formularioUsuario"; // Retorna o template do formulário
    }
}
