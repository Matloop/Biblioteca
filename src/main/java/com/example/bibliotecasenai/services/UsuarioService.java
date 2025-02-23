package com.example.bibliotecasenai.services;

import com.example.bibliotecasenai.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario salvarUsuario(Usuario usuario); // Salvar ou atualizar um usuário
    List<Usuario> findAll(); // Listar todos os usuários
    Optional<Usuario> findById(Long id); // Buscar usuário por ID
    Usuario atualizarUsuario(Usuario usuario);
    void deleteById(Long id);
}
