package com.gabriel.espacovidaspring.repository;

import com.gabriel.espacovidaspring.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    @Query(value="SELECT p from Pessoa p WHERE p.cpf = ?1")
    Pessoa findByCPF(String cpf);

    @Query(value="SELECT p from Pessoa p WHERE p.id = ?1")
    Pessoa findByID(Long id);
}
