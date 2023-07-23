package com.diarios.diarios.de.notas.repository;

import com.diarios.diarios.de.notas.entities.Aluno;
import com.diarios.diarios.de.notas.entities.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, UUID> {
}
