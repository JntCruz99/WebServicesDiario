package com.diarios.diarios.de.notas.controller;

import com.diarios.diarios.de.notas.entities.Nota;
import com.diarios.diarios.de.notas.entities.Turma;
import com.diarios.diarios.de.notas.repository.NotaRepository;
import com.diarios.diarios.de.notas.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaRepository turmaRepository;

    @Autowired
    public TurmaController(TurmaRepository turmaRepository) {
        this.turmaRepository = turmaRepository;
    }

    @GetMapping
    public List<Turma> getAllTurmas() {
        return turmaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> getTurmaById(@PathVariable UUID id) {
        Optional<Turma> turmaOptional = turmaRepository.findById(id);
        return turmaOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Turma> createTurma(@RequestBody Turma turma) {
        Turma savedTurma = turmaRepository.save(turma);
        return ResponseEntity.ok(savedTurma);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> updateTurma(@PathVariable UUID id, @RequestBody Turma turma) {
        if (!turmaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        turma.setId(id);
        Turma updatedTurma = turmaRepository.save(turma);
        return ResponseEntity.ok(updatedTurma);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTurma(@PathVariable UUID id) {
        if (!turmaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        turmaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
