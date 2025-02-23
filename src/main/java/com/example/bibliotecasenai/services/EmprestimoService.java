package com.example.bibliotecasenai.services;

import com.example.bibliotecasenai.models.Emprestimo;

import java.util.List;
import java.util.Optional;

public interface EmprestimoService {
    Emprestimo fazerEmprestimo(Emprestimo emprestimo);
    List<Emprestimo> listarEmprestimos();
    Optional<Emprestimo> buscarEmprestimoPorId(Long id);
    Emprestimo atualizarEmprestimo(Emprestimo emprestimo);
    void removerEmprestimo(Long id);


}
