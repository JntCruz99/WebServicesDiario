package com.diarios.diarios.de.notas.repository;

import com.diarios.diarios.de.notas.entities.Aluno;
import com.diarios.diarios.de.notas.entities.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, UUID> {
    Disciplina getDisciplinaById(UUID disciplinaId);
}
