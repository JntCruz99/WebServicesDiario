package com.diarios.diarios.de.notas.repository;

import com.diarios.diarios.de.notas.entities.Aluno;
import com.diarios.diarios.de.notas.entities.Frequencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FrequenciaRepository extends JpaRepository<Frequencia, UUID> {
}
