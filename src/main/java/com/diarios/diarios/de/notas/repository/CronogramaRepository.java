package com.diarios.diarios.de.notas.repository;

import com.diarios.diarios.de.notas.entities.Cronograma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CronogramaRepository extends JpaRepository<Cronograma, UUID> {
}
