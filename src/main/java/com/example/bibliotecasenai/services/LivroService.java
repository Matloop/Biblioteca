package com.example.bibliotecasenai.services;

import com.example.bibliotecasenai.models.Livro;

import java.util.List;
import java.util.Optional;

public interface LivroService {
    Livro salvarLivro(Livro livro); // Salvar ou atualizar um livro
    List<Livro> findAll(); // Listar todos os livros
    Optional<Livro> findById(Long id); // Buscar livro por ID
    Livro atualizarLivro(Livro livro);
    void deleteById(Long id); // Remover livro por ID
}
