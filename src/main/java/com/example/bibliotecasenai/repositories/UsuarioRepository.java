package com.example.bibliotecasenai.repositories;

import com.example.bibliotecasenai.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
