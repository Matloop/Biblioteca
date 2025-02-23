package com.example.bibliotecasenai.repositories;

import com.example.bibliotecasenai.models.Emprestimo;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
}
