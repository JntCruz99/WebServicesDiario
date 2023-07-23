package com.diarios.diarios.de.notas.controller;

import com.diarios.diarios.de.notas.entities.Cronograma;
import com.diarios.diarios.de.notas.entities.Disciplina;
import com.diarios.diarios.de.notas.repository.CronogramaRepository;
import com.diarios.diarios.de.notas.repository.DisciplinaRepository;
import com.diarios.diarios.de.notas.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/cronogramas")
public class CronogramaController {

    private final CronogramaRepository cronogramaRepository;
    private final DisciplinaRepository disciplinaRepository;

    public CronogramaController(CronogramaRepository cronogramaRepository, DisciplinaRepository disciplinaRepository) {
        this.cronogramaRepository = cronogramaRepository;
        this.disciplinaRepository = disciplinaRepository;
    }

    @PostMapping("/criar")
    public ResponseEntity<Cronograma> criarCronograma(@RequestBody Cronograma cronograma) {
        cronogramaRepository.save(cronograma);
        return ResponseEntity.ok(cronograma);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cronograma> buscarCronogramaPorId(@PathVariable UUID id) {
        Optional<Cronograma> cronogramaOptional = cronogramaRepository.findById(id);
        return cronogramaOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Cronograma>> listarCronogramas() {
        List<Cronograma> cronogramas = cronogramaRepository.findAll();
        return ResponseEntity.ok(cronogramas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cronograma> atualizarCronograma(@PathVariable UUID id, @RequestBody Cronograma cronogramaAtualizado) {
        Optional<Cronograma> cronogramaOptional = cronogramaRepository.findById(id);
        if (cronogramaOptional.isPresent()) {
            Cronograma cronogramaExistente = cronogramaOptional.get();
            cronogramaExistente.setDataInicio(cronogramaAtualizado.getDataInicio());
            cronogramaExistente.setDataFim(cronogramaAtualizado.getDataFim());
            Cronograma cronogramaAtualizadoSalvo = cronogramaRepository.save(cronogramaExistente);
            return ResponseEntity.ok(cronogramaAtualizadoSalvo);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCronograma(@PathVariable UUID id) {
        Optional<Cronograma> cronogramaOptional = cronogramaRepository.findById(id);
        if (cronogramaOptional.isPresent()) {
            cronogramaRepository.delete(cronogramaOptional.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

