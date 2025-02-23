package com.example.bibliotecasenai.services.Impls;

import com.example.bibliotecasenai.models.Livro;
import com.example.bibliotecasenai.repositories.EmprestimoRepository;
import com.example.bibliotecasenai.repositories.LivroRepository;
import com.example.bibliotecasenai.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroServiceImpl implements LivroService {
    private final LivroRepository livroRepository;
    @Autowired
    public LivroServiceImpl(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }


    @Override
    public Livro salvarLivro(Livro livro) {
        return livroRepository.save(livro);
    }

    @Override
    public List<Livro> findAll() {
        return livroRepository.findAll();
    }

    @Override
    public Optional<Livro> findById(Long id) {
        return livroRepository.findById(id);
    }

    @Override
    public Livro atualizarLivro(Livro livro) {
        return livroRepository.save(livro);
    }

    @Override
    public void deleteById(Long id) {
        livroRepository.deleteById(id);
    }
}
