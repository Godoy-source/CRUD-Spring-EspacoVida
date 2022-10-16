package com.gabriel.espacovidaspring.repository;

import com.gabriel.espacovidaspring.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FuncionariosRepository extends JpaRepository<Funcionario, Long> {

    @Transactional
    @Modifying()
    @Query(value = "UPDATE Funcionario SET pessoa_id = :pessoa_id WHERE funcionario_id = :funcionario_id", nativeQuery = true)
    int updateFuncionario(@Param("pessoa_id") Long pessoa_id, @Param("funcionario_id") Long funcionario_id);

}