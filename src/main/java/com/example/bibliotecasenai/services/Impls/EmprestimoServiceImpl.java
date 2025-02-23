package com.example.bibliotecasenai.services.Impls;

import com.example.bibliotecasenai.models.Emprestimo;
import com.example.bibliotecasenai.repositories.EmprestimoRepository;
import com.example.bibliotecasenai.services.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoServiceImpl implements EmprestimoService {
    private final EmprestimoRepository emprestimoRepository;
    @Autowired
    public EmprestimoServiceImpl(EmprestimoRepository emprestimoRepository) {
        this.emprestimoRepository = emprestimoRepository;
    }
    @Override
    public Emprestimo fazerEmprestimo(Emprestimo emprestimo) {
        return emprestimoRepository.save(emprestimo);
    }

    @Override
    public List<Emprestimo> listarEmprestimos() {
        return emprestimoRepository.findAll();
    }

    @Override
    public Optional<Emprestimo> buscarEmprestimoPorId(Long id) {
        return emprestimoRepository.findById(id);
    }

    @Override
    public Emprestimo atualizarEmprestimo(Emprestimo emprestimo) {
        return emprestimoRepository.save(emprestimo);
    }

    @Override
    public void removerEmprestimo(Long id) {
        emprestimoRepository.deleteById(id);
    }
}
