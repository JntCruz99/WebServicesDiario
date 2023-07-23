package com.diarios.diarios.de.notas.repository;

import com.diarios.diarios.de.notas.entities.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AulaRepository extends JpaRepository<Aula, UUID> {
}
