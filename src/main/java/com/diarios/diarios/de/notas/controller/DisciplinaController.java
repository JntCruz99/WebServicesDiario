package com.diarios.diarios.de.notas.controller;

import com.diarios.diarios.de.notas.entities.Disciplina;
import com.diarios.diarios.de.notas.entities.Turma;
import com.diarios.diarios.de.notas.repository.DisciplinaRepository;
import com.diarios.diarios.de.notas.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository disciplinaRepository;
    @Autowired
    private TurmaRepository turmaRepository;


    @GetMapping
    public List<Disciplina> getAllDisciplinas() {
        return disciplinaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> getDisciplinaById(@PathVariable UUID id) {
        Optional<Disciplina> disciplinaOptional = disciplinaRepository.findById(id);
        return disciplinaOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/criar/{turmaId}")
    public ResponseEntity<Disciplina> createDisciplina(@PathVariable UUID turmaId, @RequestBody Disciplina disciplina) {
        // Busca a turma pelo ID informado
        Optional<Turma> turmaOptional = turmaRepository.findById(turmaId);
        if (turmaOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Turma turma = turmaOptional.get();

        // Associa a disciplina à turma encontrada
        disciplina.setTurma(turma);

        // Salva a disciplina no banco de dados
        Disciplina savedDisciplina = disciplinaRepository.save(disciplina);

        return ResponseEntity.ok(savedDisciplina);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> updateDisciplina(@PathVariable UUID id, @RequestBody Disciplina disciplina) {
        if (!disciplinaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        disciplina.setId(id);
        Disciplina updatedDisciplina = disciplinaRepository.save(disciplina);
        return ResponseEntity.ok(updatedDisciplina);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisciplina(@PathVariable UUID id) {
        if (!disciplinaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        disciplinaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
