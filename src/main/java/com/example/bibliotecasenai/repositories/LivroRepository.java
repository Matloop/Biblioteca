package com.example.bibliotecasenai.repositories;

import com.example.bibliotecasenai.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
